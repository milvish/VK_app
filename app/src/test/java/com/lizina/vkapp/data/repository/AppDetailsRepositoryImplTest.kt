package com.lizina.vkapp.data.repository

import com.lizina.vkapp.data.dto.SimilarAppDto
import com.lizina.vkapp.data.network.VkAppApi
import com.lizina.vkapp.data.network.dto.AppDetailsNetworkDto
import com.lizina.vkapp.data.network.dto.CatalogItemDto
import com.lizina.vkapp.data.source.HardcodedAppsDataSource
import com.lizina.vkapp.domain.appdetails.Category
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.net.SocketTimeoutException

class AppDetailsRepositoryImplTest {

    private lateinit var api: VkAppApi
    private lateinit var source: HardcodedAppsDataSource
    private lateinit var repo: AppDetailsRepositoryImpl

    private val netDto = AppDetailsNetworkDto(
        id = "id-1", name = "Name", description = "desc",
        category = "MUSIC", iconUrl = "icon",
        developer = "Dev", ageRating = 12, size = 10f,
        screenshotUrlList = listOf("s1"),
    )
    private val catalogItem = CatalogItemDto(
        id = "id-1", name = "Name", description = "desc",
        category = "Музыка", iconUrl = "icon",
    )

    @Before
    fun setUp() {
        api = mockk()
        source = mockk(relaxed = true)
        repo = AppDetailsRepositoryImpl(api, source)
    }

    // getAppDetails — успешный путь

    @Test
    fun `getAppDetails emits server details when api succeeds`() = runTest {
        coEvery { api.getAppDetails("id-1") } returns netDto

        val items = repo.getAppDetails("id-1").toList()

        assertEquals(1, items.size)
        val item = items.first()
        assertEquals("id-1", item.id)
        assertEquals("Name", item.name)
        assertEquals(Category.MUSIC, item.category)
        assertEquals("Dev", item.developer)
        assertEquals(12, item.ageRating)
        assertEquals(10f, item.size, 0.001f)
        assertEquals(listOf("s1"), item.screenshotUrlList)
    }

    @Test
    fun `getAppDetails does not call catalog when server succeeds`() = runTest {
        coEvery { api.getAppDetails("id-1") } returns netDto

        repo.getAppDetails("id-1").toList()

        coVerify(exactly = 0) { api.getCatalog() }
    }

    @Test
    fun `getAppDetails emits exactly one item`() = runTest {
        coEvery { api.getAppDetails("id-1") } returns netDto

        val items = repo.getAppDetails("id-1").toList()

        assertEquals(1, items.size)
    }

    // getAppDetails — fallback на каталог

    @Test
    fun `getAppDetails falls back to catalog when server throws IOException`() = runTest {
        coEvery { api.getAppDetails("id-1") } throws IOException("404")
        coEvery { api.getCatalog() } returns listOf(catalogItem)

        val items = repo.getAppDetails("id-1").toList()

        assertEquals(1, items.size)
        val item = items.first()
        assertEquals("id-1", item.id)
        assertEquals(Category.MUSIC, item.category)
        // сервер /catalog не отдаёт developer
        assertEquals("", item.developer)
        assertEquals(0, item.ageRating)
    }

    @Test
    fun `getAppDetails falls back to catalog when server throws SocketTimeout`() = runTest {
        coEvery { api.getAppDetails("id-1") } throws SocketTimeoutException("timeout")
        coEvery { api.getCatalog() } returns listOf(catalogItem)

        val items = repo.getAppDetails("id-1").toList()

        assertEquals(1, items.size)
        assertEquals("id-1", items.first().id)
    }

    @Test
    fun `getAppDetails picks correct item from catalog among many`() = runTest {
        val other = catalogItem.copy(id = "other", name = "Other")
        coEvery { api.getAppDetails("id-1") } throws IOException()
        coEvery { api.getCatalog() } returns listOf(other, catalogItem, other.copy(id = "x"))

        val items = repo.getAppDetails("id-1").toList()

        assertEquals(1, items.size)
        assertEquals("id-1", items.first().id)
        assertEquals("Name", items.first().name)
    }

    @Test
    fun `getAppDetails throws when not in server nor in catalog`() = runTest {
        coEvery { api.getAppDetails("missing") } throws IOException()
        coEvery { api.getCatalog() } returns listOf(catalogItem)

        val error = assertThrows(NoSuchElementException::class.java) {
            runBlocking { repo.getAppDetails("missing").toList() }
        }
        assertTrue(error.message!!.contains("missing"))
    }

    @Test
    fun `getAppDetails throws when catalog request also fails`() = runTest {
        coEvery { api.getAppDetails("id-1") } throws IOException()
        coEvery { api.getCatalog() } throws IOException()

        assertThrows(NoSuchElementException::class.java) {
            runBlocking { repo.getAppDetails("id-1").toList() }
        }
    }

    @Test
    fun `getAppDetails throws when catalog is empty`() = runTest {
        coEvery { api.getAppDetails("id-1") } throws IOException()
        coEvery { api.getCatalog() } returns emptyList()

        assertThrows(NoSuchElementException::class.java) {
            runBlocking { repo.getAppDetails("id-1").toList() }
        }
    }

    // getApps

    @Test
    fun `getApps maps all catalog items`() = runTest {
        coEvery { api.getCatalog() } returns listOf(
            catalogItem,
            catalogItem.copy(id = "id-2", name = "Second"),
        )

        val result = repo.getApps()

        assertEquals(2, result.size)
        assertEquals("id-2", result[1].id)
        assertEquals("Second", result[1].name)
    }

    @Test
    fun `getApps returns empty list for empty catalog`() = runTest {
        coEvery { api.getCatalog() } returns emptyList()
        assertTrue(repo.getApps().isEmpty())
    }

    @Test
    fun `getApps delegates to api exactly once`() = runTest {
        coEvery { api.getCatalog() } returns listOf(catalogItem)

        repo.getApps()

        coVerify(exactly = 1) { api.getCatalog() }
    }

    // getSimilarApps

    @Test
    fun `getSimilarApps maps all items from source`() = runTest {
        val dto = SimilarAppDto("s1", "Similar", "u")
        coEvery { source.similarApps } returns listOf(dto, dto.copy(id = "s2"))

        val result = repo.getSimilarApps()

        assertEquals(2, result.size)
        assertEquals("s1", result[0].id)
        assertEquals("s2", result[1].id)
    }

    @Test
    fun `getSimilarApps returns empty list when source empty`() = runTest {
        coEvery { source.similarApps } returns emptyList()
        assertTrue(repo.getSimilarApps().isEmpty())
    }

    @Test
    fun `getSimilarApps maps iconUrl correctly`() = runTest {
        coEvery { source.similarApps } returns
                listOf(SimilarAppDto("s1", "Similar", "https://icon.png"))

        val result = repo.getSimilarApps()

        assertEquals("https://icon.png", result.first().iconUrl)
    }

    // loadRatingsSequentially

    @Test
    fun `loadRatingsSequentially returns map for all ids`() = runTest {
        coEvery { api.getRating(any()) } returns 4.5f

        val result = repo.loadRatingsSequentially(listOf("a", "b", "c"))

        assertEquals(setOf("a", "b", "c"), result.keys)
        assertTrue(result.values.all { it == 4.5f })
        coVerify(exactly = 3) { api.getRating(any()) }
    }

    @Test
    fun `loadRatingsSequentially returns empty map for empty ids`() = runTest {
        assertTrue(repo.loadRatingsSequentially(emptyList()).isEmpty())
    }

    @Test
    fun `loadRatingsSequentially preserves insertion order`() = runTest {
        coEvery { api.getRating(any()) } returns 1f

        val result = repo.loadRatingsSequentially(listOf("x", "y", "z"))

        assertEquals(listOf("x", "y", "z"), result.keys.toList())
    }

    // loadRatingsInParallel

    @Test
    fun `loadRatingsInParallel returns map for all ids`() = runTest {
        coEvery { api.getRating(any()) } returns 3.7f

        val result = repo.loadRatingsInParallel(listOf("a", "b", "c", "d"))

        assertEquals(4, result.size)
        assertEquals(setOf("a", "b", "c", "d"), result.keys)
        coVerify(exactly = 4) { api.getRating(any()) }
    }

    @Test
    fun `loadRatingsInParallel returns empty map for empty ids`() = runTest {
        assertTrue(repo.loadRatingsInParallel(emptyList()).isEmpty())
    }

    @Test
    fun `loadRatingsInParallel does not call api for empty ids`() = runTest {
        repo.loadRatingsInParallel(emptyList())

        coVerify(exactly = 0) { api.getRating(any()) }
    }

    @Test
    fun `loadRatingsInParallel handles duplicates correctly`() = runTest {
        coEvery { api.getRating(any()) } returns 2f

        val result = repo.loadRatingsInParallel(listOf("a", "a", "b"))

        assertEquals(2, result.size)
        assertEquals(setOf("a", "b"), result.keys)
    }
}