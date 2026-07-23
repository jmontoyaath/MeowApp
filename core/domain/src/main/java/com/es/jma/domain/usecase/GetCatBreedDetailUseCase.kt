package com.es.jma.domain.usecase

import com.es.jma.data.repository.CatRepository
import com.es.jma.domain.CatSuspendedUseCase
import com.es.jma.domain.di.IoDispatcher
import com.es.jma.model.Breed
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCatBreedDetailUseCase @Inject constructor(
    private val repository: CatRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CatSuspendedUseCase<String, Breed>(dispatcher) {

    override suspend fun execute(parameters: String): Result<Breed> {
        return repository.getCatBreedDetail(parameters)
    }
}