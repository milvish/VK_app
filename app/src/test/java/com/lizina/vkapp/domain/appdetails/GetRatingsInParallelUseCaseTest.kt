package com.lizina.vkapp.domain.appdetails

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class GetRatingsInParallelUseCaseTest {

    private val repo: AppDetailsRepository = mockk()
    private val useCase = GetRatingsInParallelUseCase(repo)

    @Test
    fun `invoke forwards ids to repository`() = runTest {
        val ids = listOf("a", "b")
        coEvery { repo.loadRatingsInParallel(ids) } returns mapOf("a" to 2f, "b" to 3f)

        assertEquals(mapOf("a" to 2f, "b" to 3f), useCase(ids))
    }

    @Test
    fun `invoke calls repository once`() = runTest {
        coEvery { repo.loadRatingsInParallel(any()) } returns emptyMap()
        useCase(listOf("a"))
        coVerify(exactly = 1) { repo.loadRatingsInParallel(any()) }
    }

    @Test
    fun `invoke returns empty map for empty ids`() = runTest {
        coEvery { repo.loadRatingsInParallel(emptyList()) } returns emptyMap()
        assertEquals(emptyMap<String, Float>(), useCase(emptyList()))
    }

    @Test
    fun `invoke propagates exception`() = runTest {
        coEvery { repo.loadRatingsInParallel(any()) } throws RuntimeException("parallel")

        val e = assertThrows(RuntimeException::class.java) {
            runTest { useCase(listOf("a")) }
        }
        assertEquals("parallel", e.message)
    }

    @Test
    fun `invoke returns same size map as ids`() = runTest {
        val ids = listOf("a", "b", "c", "d")
        coEvery { repo.loadRatingsInParallel(ids) } returns ids.associateWith { 4.2f }
        assertEquals(ids.size, useCase(ids).size)
    }
}