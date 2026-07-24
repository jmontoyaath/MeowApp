package com.es.jma.domain.usecase

import com.es.jma.data.repository.CatRepository
import com.es.jma.domain.CatSuspendedUseCase
import com.es.jma.domain.di.IoDispatcher
import com.es.jma.model.Breed
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SearchCatBreedsUseCase @Inject constructor(
    private val repository: CatRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : CatSuspendedUseCase<String, List<Breed>>(dispatcher) {

    override suspend fun execute(parameters: String): Result<List<Breed>> {
        return repository.searchBreedCat(parameters)
    }
}