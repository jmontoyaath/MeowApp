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

private const val THEME_PREFERENCES_NAME = "theme_preferences"
private val Context.themeDataStore by preferencesDataStore(name = THEME_PREFERENCES_NAME)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    fun provideThemeDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.themeDataStore
}