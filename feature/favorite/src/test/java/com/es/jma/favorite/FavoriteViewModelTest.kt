package com.es.jma.favorite

import app.cash.turbine.test
import com.es.jma.domain.usecase.GetFavoriteCatsUseCase
import com.es.jma.domain.usecase.ValidateFavoriteUseCase
import com.es.jma.testing.MainDispatcherRule
import com.es.jma.testing.fakeCat
import com.es.jma.testing.fakeCatTwo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getFavoriteCats = mockk<GetFavoriteCatsUseCase>()
    private val validateFavorite = mockk<ValidateFavoriteUseCase>()

    private lateinit var viewModel: FavoriteViewModel

    @Test
    fun `init getFavorites and updates state to Success`() = runTest {
        val catsList = listOf(
            fakeCat,
            fakeCatTwo
        )
        coEvery { getFavoriteCats(Unit) } returns flowOf(catsList)

        viewModel = FavoriteViewModel(getFavoriteCats, validateFavorite)

        val expectedState = FavoriteUiState.Success(data = catsList)
        assertEquals(expectedState, viewModel.uiState.value)
    }

    @Test
    fun `when fail at deleting a favorite, it show a ShowErrorFavorites`() = runTest {
        every { getFavoriteCats(Unit) } returns flowOf(listOf(fakeCat))
        coEvery { validateFavorite(any()) } returns Result.failure(IOException())

        viewModel = FavoriteViewModel(getFavoriteCats, validateFavorite)

        viewModel.action.test {
            viewModel.onDeleteFavorite(fakeCat)
            assertEquals(FavoriteAction.ErrorDeletingFavorite, awaitItem())
        }
    }
}