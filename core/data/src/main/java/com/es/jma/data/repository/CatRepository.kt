package com.es.jma.data.repository

import com.es.jma.model.Breed
import com.es.jma.model.CatInfo
import com.es.jma.utils.constants.LIMIT_10
import com.es.jma.utils.constants.PAGE_1

interface CatRepository {
    suspend fun getCatImages(limit: Int = LIMIT_10, page: Int = PAGE_1): Result<List<CatInfo>>
    suspend fun getCatBreedDetail(id: String): Result<Breed>
    suspend fun searchBreedCat(query: String): Result<List<Breed>>
    suspend fun getImagesByBreed(idBreed: String): Result<List<CatInfo>>
}