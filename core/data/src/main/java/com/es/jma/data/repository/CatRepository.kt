package com.es.jma.data.repository

import com.es.jma.model.Breed
import com.es.jma.model.CatInfo

interface CatRepository {
    suspend fun getCatImages(limit: Int = 10, page: Int = 1): Result<List<CatInfo>>
    suspend fun getCatBreedDetail(id: String): Result<Breed>
    suspend fun searchBreedCat(query: String): Result<List<Breed>>
    suspend fun getImagesByBreed(idBreed: String): Result<List<CatInfo>>
}