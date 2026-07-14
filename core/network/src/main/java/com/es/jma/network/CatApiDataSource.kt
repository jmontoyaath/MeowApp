package com.es.jma.network

import com.es.jma.network.modal.CatBreed
import com.es.jma.network.modal.RandomCat

interface CatApiDataSource {
    suspend fun getRandomCats(limit: Int = 10, page: Int = 1): Result<List<RandomCat>>
    suspend fun getCatByImage(idImage: String): Result<RandomCat>
    suspend fun getCatBreeds(): Result<List<CatBreed>>
}