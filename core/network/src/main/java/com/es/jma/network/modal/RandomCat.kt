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
    val breeds: List<CatBreed>?
)

fun RandomCat.asExternalModel(): CatInfo =
    CatInfo(
        id = id,
        url = url,
        idBreed = breeds?.get(0)?.id ?: "",
        name = breeds?.get(0)?.name ?: "",
        description = breeds?.get(0)?.description ?: "",
        temperament = breeds?.get(0)?.temperament ?: "",
        origin = breeds?.get(0)?.origin ?: "",
    )