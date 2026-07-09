package com.es.jma.data.repository

import com.es.jma.model.Breed
import com.es.jma.model.CatInfo

interface CatRepository {
    suspend fun getCatImages(limit: Int = 10): Result<List<CatInfo>>
    suspend fun getCatByImage(idImage: String): Result<CatInfo>
    suspend fun getCatBreeds(): Result<List<Breed>>
}