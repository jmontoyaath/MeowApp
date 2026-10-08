package com.es.jma.data.di

import com.es.jma.data.util.NetworkMonitor
import com.es.jma.data.repository.CatRepository
import com.es.jma.data.repository.CatRepositoryImpl
import com.es.jma.data.repository.FavoriteRepository
import com.es.jma.data.repository.FavoriteRepositoryImpl
import com.es.jma.data.repository.UserPreferencesRepository
import com.es.jma.data.repository.UserPreferencesRepositoryImpl
import com.es.jma.data.util.ConnectivityManagerNetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindsCatRepository(
        catRepositoryImpl: CatRepositoryImpl
    ): CatRepository

    @Binds
    @Singleton
    fun bindsFavoriteRepository(
        favoriteRepositoryImpl: FavoriteRepositoryImpl
    ): FavoriteRepository

    @Binds
    abstract fun bindThemePreferencesRepository(
        impl: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository

    @Binds
    @Singleton
    abstract fun bindNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor
}