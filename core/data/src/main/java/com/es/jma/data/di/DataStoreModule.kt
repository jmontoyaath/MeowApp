package com.es.jma.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

private val Context.themeDataStore by preferencesDataStore(name = "theme_preferences")

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    fun provideThemeDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.themeDataStore
}