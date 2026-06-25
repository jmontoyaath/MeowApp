package com.es.jma.network.modal

import com.es.jma.model.CatInfo
import kotlinx.serialization.Serializable

/**
 * Network representation of [com.es.jma.model.CatInfo]
 */
@Serializable
data class RandomCat(
    val id: String,
    val url: String,
    val breeds: List<CatBreed>
)

fun RandomCat.asExternalModel(): CatInfo =
    CatInfo(
        id = id,
        url = url,
        breed = breeds[0].asExternalModel()
    )