package com.es.jma.domain.usecase

import com.es.jma.data.di.IoDispatcher
import com.es.jma.data.repository.CatRepository
import com.es.jma.domain.CatSuspendedUseCase
import com.es.jma.model.CatInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetImagesCatByBreedUseCase @Inject constructor(
    private val repository: CatRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CatSuspendedUseCase<String, List<CatInfo>>(dispatcher) {

    override suspend fun execute(parameters: String): Result<List<CatInfo>> {
        return repository.getImagesByBreed(idBreed = parameters)
    }
}