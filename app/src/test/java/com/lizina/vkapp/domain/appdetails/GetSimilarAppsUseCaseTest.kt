package com.lizina.vkapp.domain.appdetails

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class GetSimilarAppsUseCaseTest {

    private val repo: AppDetailsRepository = mockk()
    private val useCase = GetSimilarAppsUseCase(repo)

    private val similar = SimilarApp("s1", "Similar", "u")

    @Test
    fun `invoke delegates to repository`() = runTest {
        coEvery { repo.getSimilarApps() } returns listOf(similar)
        assertEquals(listOf(similar), useCase())
    }

    @Test
    fun `invoke calls repository once`() = runTest {
        coEvery { repo.getSimilarApps() } returns listOf(similar)
        useCase()
        coVerify(exactly = 1) { repo.getSimilarApps() }
    }

    @Test
    fun `invoke returns empty list when repository empty`() = runTest {
        coEvery { repo.getSimilarApps() } returns emptyList()
        assertEquals(emptyList<SimilarApp>(), useCase())
    }

    @Test
    fun `invoke propagates exception`() = runTest {
        coEvery { repo.getSimilarApps() } throws RuntimeException("fail")

        val e = assertThrows(RuntimeException::class.java) { runTest { useCase() } }
        assertEquals("fail", e.message)
    }

    @Test
    fun `invoke preserves order`() = runTest {
        val list = listOf(similar, similar.copy(id = "s2"), similar.copy(id = "s3"))
        coEvery { repo.getSimilarApps() } returns list
        assertEquals(listOf("s1", "s2", "s3"), useCase().map { it.id })
    }
}