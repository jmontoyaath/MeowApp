package com.es.jma.domain

import com.es.jma.data.repository.CatRepository
import com.es.jma.model.Breed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCatBreedsUseCase @Inject constructor(
    private val repository: CatRepository
) {
    operator fun invoke(): Flow<List<Breed>> = repository.getCatBreeds()
}