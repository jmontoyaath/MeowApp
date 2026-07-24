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
    override suspend fun getCatImages(limit: Int, page: Int): Result<List<CatInfo>> {
        return networkDataSource.getRandomCats(limit = limit, page = page).map(catMapper::mapList)
    }

    override suspend fun getCatBreedDetail(id: String): Result<Breed> {
        return networkDataSource.getCatBreedDetail(id = id).map(catMapper::mapBreed)
    }

    override suspend fun searchBreedCat(query: String): Result<List<Breed>> {
        return networkDataSource.searchBreedCat(query = query).map (catMapper::mapBreeds )
    }

    override suspend fun getImagesByBreed(idBreed: String): Result<List<CatInfo>> {
        return networkDataSource.getImagesByBreed(breedId = idBreed).map(catMapper::mapList)
    }
}