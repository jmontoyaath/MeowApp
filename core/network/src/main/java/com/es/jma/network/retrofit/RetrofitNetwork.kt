package com.es.jma.network.retrofit

import androidx.core.os.trace
import com.es.jma.network.BuildConfig
import com.es.jma.network.CatApiDataSource
import com.es.jma.network.model.CatBreed
import com.es.jma.network.model.RandomCat
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

    @GET(value = GET_BREED_DETAIL)
    suspend fun getBreedDetail(@Path("breedId") id: String): CatBreed

    @GET(value = GET_BREED_SEARCH)
    suspend fun searchBreeds(@Query("q") query: String): List<CatBreed>

    @GET(GET_RANDOM_CATS)
    suspend fun getImagesByBreed(
        @Query("breed_ids") breedId: String,
        @Query("limit") limit: Int = 5
    ): List<RandomCat>

    companion object {
        const val GET_RANDOM_CATS = "images/search"
        const val GET_BREED_DETAIL = "breeds/{breedId}"
        const val GET_BREED_SEARCH = "breeds/search"
    }
}

private const val THE_CAT_API_URL = BuildConfig.API_URL

@Singleton
class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : CatApiDataSource {

    private val networkApi = trace("RetrofitMeowNetwork") {
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

    override suspend fun getCatBreedDetail(id: String): Result<CatBreed> =
        kotlin.runCatching { networkApi.getBreedDetail(id = id) }

    override suspend fun searchBreedCat(query: String): Result<List<CatBreed>> =
        kotlin.runCatching { networkApi.searchBreeds(query = query) }

    override suspend fun getImagesByBreed(breedId: String): Result<List<RandomCat>> =
        kotlin.runCatching { networkApi.getImagesByBreed(breedId) }
}