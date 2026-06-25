package com.es.jma.data.di

import com.es.jma.data.repository.CatRepository
import com.es.jma.data.repository.CatRepositoryImpl
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
}