package com.es.jma.domain.usecase

import com.es.jma.data.repository.CatRepository
import com.es.jma.domain.CatUseCase
import com.es.jma.domain.di.IoDispatcher
import com.es.jma.model.Breed
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCatBreedsUseCase @Inject constructor(
    private val repository: CatRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CatUseCase<Unit, List<Breed>>(dispatcher) {

    override suspend fun execute(parameters: Unit): Result<List<Breed>> {
        return repository.getCatBreeds()
    }
}