package com.es.jma.network

import com.es.jma.network.modal.CatBreed
import com.es.jma.network.modal.RandomCat

interface CatApiDataSource {
    suspend fun getRandomCats(limit: Int = 10): List<RandomCat>
    suspend fun getCatByImage(idImage: String): RandomCat
    suspend fun getCatBreeds(): List<CatBreed>
}