package com.es.jma.data.mapper

import com.es.jma.model.Breed
import com.es.jma.model.CatInfo
import com.es.jma.network.modal.CatBreed
import com.es.jma.network.modal.RandomCat
import com.es.jma.network.modal.asExternalModel
import javax.inject.Inject

class CatMapper @Inject constructor() : Mapper<RandomCat, CatInfo> {

    override fun map(params: RandomCat): CatInfo = params.asExternalModel()

    fun mapList(params: List<RandomCat>): List<CatInfo> = params.map {
        it.asExternalModel()
    }

    fun mapBreeds(params: List<CatBreed>): List<Breed> = params.map {
        it.asExternalModel()
    }
}

fun interface Mapper<in E, out T> {
    fun map(params: E): T
}