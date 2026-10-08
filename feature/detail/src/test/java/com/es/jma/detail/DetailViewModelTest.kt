package com.es.jma.detail

import app.cash.turbine.test
import com.es.jma.domain.usecase.GetCatBreedDetailUseCase
import com.es.jma.testing.MainDispatcherRule
import com.es.jma.testing.fakeBreed
import com.es.jma.testing.fakeCat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getCatBreedDetail: GetCatBreedDetailUseCase = mockk()

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        viewModel = DetailViewModel(getCatBreedDetail)
    }

    @Test
    fun `initial state is Loading`() {
        assertEquals(DetailUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `getCatDetail emits Success with data when use case succeeds`() = runTest {
        coEvery { getCatBreedDetail.invoke(fakeCat.idBreed) } returns Result.success(fakeBreed)

        viewModel.getCatDetail(fakeCat.idBreed)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertTrue(state is DetailUiState.Success)
        assertEquals(fakeBreed, (state as DetailUiState.Success).data)
        coVerify(exactly = 1) { getCatBreedDetail.invoke(fakeCat.idBreed) }
    }

    @Test
    fun `getCatDetail emits Error when use case fails`() = runTest {
        coEvery { getCatBreedDetail.invoke("1") } returns Result.failure(RuntimeException("boom"))

        viewModel.getCatDetail("1")
        advanceUntilIdle()

        assertEquals(DetailUiState.Error, viewModel.uiState.value)
    }

    @Test
    fun `state transitions Loading to Success`() = runTest {
        coEvery { getCatBreedDetail.invoke(fakeCat.idBreed) } returns Result.success(fakeBreed)

        viewModel.uiState.test {
            assertEquals(DetailUiState.Loading, awaitItem())

            viewModel.getCatDetail(fakeCat.idBreed)
            advanceUntilIdle()

            assertEquals(DetailUiState.Success(fakeBreed), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `state transitions Loading to Error`() = runTest {
        coEvery { getCatBreedDetail.invoke("1") } returns Result.failure(RuntimeException())

        viewModel.uiState.test {
            assertEquals(DetailUiState.Loading, awaitItem())

            viewModel.getCatDetail("1")
            advanceUntilIdle()

            assertEquals(DetailUiState.Error, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `retry after error goes back to Success`() = runTest {
        coEvery { getCatBreedDetail.invoke("1") } returnsMany listOf(
            Result.failure(RuntimeException()),
            Result.success(fakeBreed)
        )

        viewModel.getCatDetail("1")
        advanceUntilIdle()
        assertEquals(DetailUiState.Error, viewModel.uiState.value)

        viewModel.getCatDetail("1")
        advanceUntilIdle()
        assertEquals(DetailUiState.Success(fakeBreed), viewModel.uiState.value)
    }

}