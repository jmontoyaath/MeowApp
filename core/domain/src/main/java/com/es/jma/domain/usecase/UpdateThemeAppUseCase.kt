package com.es.jma.domain.usecase

import com.es.jma.data.di.IoDispatcher
import com.es.jma.data.repository.UserPreferencesRepository
import com.es.jma.domain.CatSuspendedUseCase
import com.es.jma.model.ThemeConfigEnum
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UpdateThemeAppUseCase @Inject constructor(
    private val repository: UserPreferencesRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CatSuspendedUseCase<ThemeConfigEnum, Unit>(dispatcher) {

    override suspend fun execute(parameters: ThemeConfigEnum): Result<Unit> {
        repository.setThemeConfig(parameters)
        return Result.success(Unit)
    }
}