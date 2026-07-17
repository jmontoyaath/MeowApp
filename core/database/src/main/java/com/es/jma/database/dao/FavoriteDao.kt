package com.es.jma.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.es.jma.database.model.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT id FROM cats ORDER BY addedAt DESC")
    fun getAllFavoritesIds(): Flow<List<String>>

    @Query("SELECT * FROM cats ORDER BY addedAt DESC")
    fun getAllFavorites(): Flow<List<CatEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM cats WHERE id = :catId)")
    fun isFavorite(catId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cat: CatEntity)

    @Query("DELETE FROM cats WHERE id = :catId")
    suspend fun delete(catId: String)
}