package com.es.jma.domain.usecase

import com.es.jma.data.repository.FavoriteRepository
import com.es.jma.domain.CatSuspendedUseCase
import com.es.jma.domain.di.IoDispatcher
import com.es.jma.model.CatInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ValidateFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CatSuspendedUseCase<ValidateCatParam, Unit>(dispatcher) {

    override suspend fun execute(parameters: ValidateCatParam): Result<Unit> {
        return runCatching {
            if (parameters.isFavorite) {
                repository.saveFavoriteCat(parameters.catInfo)
            } else repository.removeFavoriteCat(parameters.catInfo.id)
        }
    }
}

data class ValidateCatParam(
    val catInfo: CatInfo,
    val isFavorite: Boolean = false
)