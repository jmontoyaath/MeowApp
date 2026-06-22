package com.es.jma.data.repository

import com.es.jma.model.Breed
import com.es.jma.model.CatInfo
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    fun getCatImages(limit: Int = 10): Flow<List<CatInfo>>
    fun getCatByImage(idImage: String): Flow<CatInfo>
    fun getCatBreeds(): Flow<List<Breed>>
}