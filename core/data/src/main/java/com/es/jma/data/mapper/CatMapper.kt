package com.es.jma.data.mapper

import com.es.jma.model.Breed
import com.es.jma.model.CatInfo
import com.es.jma.network.model.CatBreed
import com.es.jma.network.model.RandomCat
import com.es.jma.network.model.asExternalModel
import javax.inject.Inject

class CatMapper @Inject constructor() : Mapper<RandomCat, CatInfo> {

    override fun map(params: RandomCat): CatInfo = params.asExternalModel()

    fun mapList(params: List<RandomCat>): List<CatInfo> = params.map {
        it.asExternalModel()
    }

    fun mapBreed(params: CatBreed): Breed = params.asExternalModel()

    fun mapBreeds(params: List<CatBreed>): List<Breed> = params.map {
        it.asExternalModel()
    }
}

fun interface Mapper<in E, out T> {
    fun map(params: E): T
}