package com.es.jma.domain.usecase

import com.es.jma.data.di.IoDispatcher
import com.es.jma.data.repository.UserPreferencesRepository
import com.es.jma.domain.CatSuspendedUseCase
import com.es.jma.model.UnitSystemEnum
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UpdateUnitSystemAppUseCase @Inject constructor(
    private val repository: UserPreferencesRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CatSuspendedUseCase<UnitSystemEnum, Unit>(dispatcher) {

    override suspend fun execute(parameters: UnitSystemEnum): Result<Unit> {
        repository.setUnitSystem(parameters)
        return Result.success(Unit)
    }
}