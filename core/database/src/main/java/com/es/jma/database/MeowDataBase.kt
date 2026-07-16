package com.es.jma.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.es.jma.database.dao.FavoriteDao
import com.es.jma.database.model.CatEntity
import com.es.jma.database.util.InstantConverter

@Database(
    entities = [CatEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    InstantConverter::class,
)
internal abstract class MeowDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}