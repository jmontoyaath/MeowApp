package com.es.jma.domain.usecase

import com.es.jma.data.repository.CatRepository
import com.es.jma.testing.fakeBreed
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCatBreedDetailUseCaseTest {
    private val repository = mockk<CatRepository>()
    private val useCase = GetCatBreedDetailUseCase(repository, UnconfinedTestDispatcher())

    @Test
    fun `get cat breed detail with success result`() = runTest {
        coEvery { useCase.invoke("1") } returns Result.success(fakeBreed)
        val result = useCase("1")
        assertTrue(result.isSuccess)
    }

    @Test
    fun `get cat breed detail with failure result`() = runTest {
        coEvery { useCase.invoke("1") } returns Result.failure(RuntimeException("boom"))
        val result = useCase("1")
        assertTrue(result.isFailure)
    }
}