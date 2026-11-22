package com.lizina.vkapp.domain.appdetails

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class GetRatingsSequentiallyUseCaseTest {

    private val repo: AppDetailsRepository = mockk()
    private val useCase = GetRatingsSequentiallyUseCase(repo)

    @Test
    fun `invoke forwards ids to repository`() = runTest {
        val ids = listOf("a", "b")
        coEvery { repo.loadRatingsSequentially(ids) } returns mapOf("a" to 4f, "b" to 5f)

        assertEquals(mapOf("a" to 4f, "b" to 5f), useCase(ids))
    }

    @Test
    fun `invoke calls repository once`() = runTest {
        coEvery { repo.loadRatingsSequentially(any()) } returns emptyMap()
        useCase(listOf("a"))
        coVerify(exactly = 1) { repo.loadRatingsSequentially(any()) }
    }

    @Test
    fun `invoke returns empty map for empty ids`() = runTest {
        coEvery { repo.loadRatingsSequentially(emptyList()) } returns emptyMap()
        assertEquals(emptyMap<String, Float>(), useCase(emptyList()))
    }

    @Test
    fun `invoke propagates exception`() = runTest {
        coEvery { repo.loadRatingsSequentially(any()) } throws RuntimeException("net")

        val e = assertThrows(RuntimeException::class.java) {
            runTest { useCase(listOf("a")) }
        }
        assertEquals("net", e.message)
    }

    @Test
    fun `invoke returns all requested keys`() = runTest {
        val ids = listOf("a", "b", "c")
        coEvery { repo.loadRatingsSequentially(ids) } returns ids.associateWith { 1f }
        assertEquals(setOf("a", "b", "c"), useCase(ids).keys)
    }
}