package com.es.jma.data.repository

import com.es.jma.model.CatInfo
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getFavoriteIds(): Flow<List<String>>
    fun getFavoriteCats(): Flow<List<CatInfo>>
    suspend fun saveFavoriteCat(catFavorite: CatInfo)
    suspend fun removeFavoriteCat(idCat: String)
}