package com.lizina.vkapp.domain.appdetails

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class GetAppsUseCaseTest {

    private val repo: AppDetailsRepository = mockk()
    private val useCase = GetAppsUseCase(repo)

    private val app = AppDetails(
        id = "1", name = "App", developer = "Dev",
        category = Category.GAME, ageRating = 12, size = 1f,
        iconUrl = "i", screenshotUrlList = null, description = "d",
    )

    @Test
    fun `invoke delegates to repository and returns result`() = runTest {
        coEvery { repo.getApps() } returns listOf(app)
        assertEquals(listOf(app), useCase())
    }

    @Test
    fun `invoke calls repository exactly once`() = runTest {
        coEvery { repo.getApps() } returns listOf(app)
        useCase()
        coVerify(exactly = 1) { repo.getApps() }
    }

    @Test
    fun `invoke returns empty list when repository empty`() = runTest {
        coEvery { repo.getApps() } returns emptyList()
        assertEquals(emptyList<AppDetails>(), useCase())
    }

    @Test
    fun `invoke propagates exception from repository`() = runTest {
        coEvery { repo.getApps() } throws RuntimeException("boom")

        val e = assertThrows(RuntimeException::class.java) { runTest { useCase() } }
        assertEquals("boom", e.message)
    }

    @Test
    fun `invoke preserves order of items`() = runTest {
        val second = app.copy(id = "2", name = "B")
        coEvery { repo.getApps() } returns listOf(app, second)
        assertEquals(listOf("1", "2"), useCase().map { it.id })
    }
}