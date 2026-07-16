package com.es.jma.data.repository

import com.es.jma.data.mapper.FavoriteMapper
import com.es.jma.database.dao.FavoriteDao
import com.es.jma.model.CatInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: FavoriteDao,
    private val mapper: FavoriteMapper
) : FavoriteRepository {

    override fun getFavoriteIds(): Flow<List<String>> {
        return localDataSource.getAllFavorites()
    }

    override suspend fun saveFavoriteCat(catFavorite: CatInfo) {
        localDataSource.insert(mapper.map(catFavorite))
    }

    override suspend fun removeFavoriteCat(idCat: String) {
        localDataSource.delete(catId = idCat)
    }
}