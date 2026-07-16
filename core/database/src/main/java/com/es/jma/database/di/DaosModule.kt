package com.es.jma.database.di

import com.es.jma.database.MeowDataBase
import com.es.jma.database.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesFavoriteDao(
        database: MeowDataBase,
    ): FavoriteDao = database.favoriteDao()
}