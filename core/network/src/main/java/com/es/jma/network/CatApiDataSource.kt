package com.es.jma.network

import com.es.jma.network.model.CatBreed
import com.es.jma.network.model.RandomCat

interface CatApiDataSource {
    suspend fun getRandomCats(limit: Int = 10, page: Int = 1): Result<List<RandomCat>>
    suspend fun getCatBreedDetail(id: String): Result<CatBreed>
    suspend fun searchBreedCat(query: String): Result<List<CatBreed>>
    suspend fun getImagesByBreed(breedId: String): Result<List<RandomCat>>
}