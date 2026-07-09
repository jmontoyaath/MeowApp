package com.es.jma.data.repository

import com.es.jma.data.mapper.CatMapper
import com.es.jma.model.Breed
import com.es.jma.model.CatInfo
import com.es.jma.network.CatApiDataSource
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val networkDataSource: CatApiDataSource,
    private val catMapper: CatMapper
) : CatRepository {
    override suspend fun getCatImages(limit: Int): Result<List<CatInfo>> {
        return networkDataSource.getRandomCats(limit = limit).map(catMapper::mapList)
    }

    override suspend fun getCatByImage(idImage: String): Result<CatInfo> {
        return networkDataSource.getCatByImage(idImage = idImage).map(catMapper::map)
    }

    override suspend fun getCatBreeds(): Result<List<Breed>> {
        return networkDataSource.getCatBreeds().map(catMapper::mapBreeds)
    }
}