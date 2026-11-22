package com.lizina.vkapp.domain.appdetails

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class GetAppDetailsUseCaseTest {

    private val repo: AppDetailsRepository = mockk()
    private val useCase = GetAppDetailsUseCase(repo)

    private val app = AppDetails(
        id = "1", name = "App", developer = "Dev",
        category = Category.GAME, ageRating = 12, size = 1f,
        iconUrl = "i", screenshotUrlList = null, description = "d",
    )

    @Test
    fun `invoke forwards id to repository`() = runTest {
        coEvery { repo.getAppDetails("42") } returns flowOf(app)

        val items = useCase("42").toList()

        assertEquals(listOf(app), items)
        coVerify(exactly = 1) { repo.getAppDetails("42") }
    }

    @Test
    fun `invoke returns flow emitting domain model`() = runTest {
        coEvery { repo.getAppDetails(any()) } returns flowOf(app)

        val items = useCase("1").toList()

        assertEquals(1, items.size)
        assertEquals("App", items.first().name)
    }

    @Test
    fun `invoke propagates flow error`() = runTest {
        coEvery { repo.getAppDetails(any()) } returns flow {
            throw IllegalStateException("not found")
        }

        val e = assertThrows(IllegalStateException::class.java) {
            runBlocking { useCase("1").toList() }
        }
        assertEquals("not found", e.message)
    }

    @Test
    fun `invoke uses exactly one id per call`() = runTest {
        coEvery { repo.getAppDetails("a") } returns flowOf(app.copy(id = "a"))
        coEvery { repo.getAppDetails("b") } returns flowOf(app.copy(id = "b"))

        assertEquals("a", useCase("a").toList().first().id)
        assertEquals("b", useCase("b").toList().first().id)
    }

    @Test
    fun `invoke calls repository at flow creation time`() = runTest {
        coEvery { repo.getAppDetails(any()) } returns flowOf(app)
        useCase("x")
        coVerify(exactly = 1) { repo.getAppDetails("x") }
    }
}