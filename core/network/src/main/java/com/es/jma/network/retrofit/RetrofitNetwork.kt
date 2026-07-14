package com.es.jma.network.retrofit

import androidx.core.os.trace
import com.es.jma.network.BuildConfig
import com.es.jma.network.CatApiDataSource
import com.es.jma.network.modal.CatBreed
import com.es.jma.network.modal.RandomCat
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

class CatApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("x-api-key", BuildConfig.API_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}

private interface RetrofitTheCatApi {
    @GET(value = GET_RANDOM_CATS)
    suspend fun getRandomCats(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("has_breeds") hasBreeds: Int = 1,
        @Query("order") order: String = "ASC"
    ): List<RandomCat>

    @GET(value = GET_CAT_BY_IMAGE)
    suspend fun getCatByImage(
        @Path(value = "id") idImage: String,
    ): RandomCat

    @GET(value = GET_CAT_BREEDS)
    suspend fun getCatBreeds(): List<CatBreed>

    companion object {
        const val GET_RANDOM_CATS = "images/search"
        const val GET_CAT_BY_IMAGE = "images/{id}"
        const val GET_CAT_BREEDS = "breeds"
    }
}

private const val THE_CAT_API_URL = BuildConfig.API_URL

@Singleton
class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : CatApiDataSource {

    private val networkApi = trace("RetrofitNiaNetwork") {
        Retrofit.Builder()
            .baseUrl(THE_CAT_API_URL)
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitTheCatApi::class.java)
    }

    override suspend fun getRandomCats(limit: Int, page: Int): Result<List<RandomCat>> =
        kotlin.runCatching { networkApi.getRandomCats(limit = limit, page = page) }

    override suspend fun getCatByImage(idImage: String): Result<RandomCat> =
        kotlin.runCatching { networkApi.getCatByImage(idImage = idImage) }

    override suspend fun getCatBreeds(): Result<List<CatBreed>> =
        kotlin.runCatching { networkApi.getCatBreeds() }
}