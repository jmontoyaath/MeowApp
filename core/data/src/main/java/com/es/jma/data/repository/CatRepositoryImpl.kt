package com.es.jma.data.repository

import com.es.jma.model.Breed
import com.es.jma.model.CatInfo
import com.es.jma.network.CatApiDataSource
import com.es.jma.network.modal.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val networkDataSource: CatApiDataSource
) : CatRepository {
    override fun getCatImages(limit: Int): Flow<List<CatInfo>> = flow {
        val networkCats = networkDataSource.getRandomCats(limit = limit)
        val domainCats = networkCats.map { it.asExternalModel() }
        emit(domainCats)
    }

    override fun getCatByImage(idImage: String): Flow<CatInfo> = flow {
        val networkImage = networkDataSource.getCatByImage(idImage = idImage)
        val domainImage = networkImage.asExternalModel()
        emit(domainImage)
    }

    override fun getCatBreeds(): Flow<List<Breed>> = flow {
        val networkBreeds = networkDataSource.getCatBreeds()
        val domainBreeds = networkBreeds.map { it.asExternalModel() }
        emit(domainBreeds)
    }
}