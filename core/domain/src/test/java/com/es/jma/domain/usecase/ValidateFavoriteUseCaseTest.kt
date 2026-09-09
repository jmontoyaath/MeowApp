package com.es.jma.domain.usecase

import com.es.jma.data.repository.FavoriteRepository
import com.es.jma.testing.fakeCat
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ValidateFavoriteUseCaseTest {
    private val repository = mockk<FavoriteRepository>()
    private val useCase = ValidateFavoriteUseCase(repository, UnconfinedTestDispatcher())

    @Test
    fun `when isFavorite is true, save the cat`() = runTest {
        coEvery { repository.saveFavoriteCat(any()) } just Runs
        val result = useCase(ValidateCatParam(catInfo = fakeCat, isFavorite = true))
        coVerify { repository.saveFavoriteCat(fakeCat) }
        assertTrue(result.isSuccess)
    }

    @Test
    fun `when the repo fail, the Result ir failure (don't work)`() = runTest {
        coEvery { repository.saveFavoriteCat(any()) } throws IOException()
        val result = useCase(ValidateCatParam(catInfo = fakeCat, isFavorite = true))
        assertTrue(result.isFailure)
    }
}