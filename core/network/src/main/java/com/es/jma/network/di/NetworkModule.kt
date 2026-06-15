package com.es.jma.network.di

import androidx.core.os.trace
import com.es.jma.network.BuildConfig
import com.es.jma.network.retrofit.CatApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): CatApiKeyInterceptor = CatApiKeyInterceptor()

    @Provides
    @Singleton
    fun okHttpCallFactory(
        apiKeyInterceptor: CatApiKeyInterceptor
    ): Call.Factory = trace("MeowOkHttpClient") {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .addInterceptor(apiKeyInterceptor)
            .build()
    }
}