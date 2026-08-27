package com.es.jma.network.model

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
        idBreed = breeds?.firstOrNull()?.id ?: "",
        name = breeds?.firstOrNull()?.name ?: "",
        description = breeds?.firstOrNull()?.description ?: "",
        temperament = breeds?.firstOrNull()?.temperament ?: "",
        origin = breeds?.firstOrNull()?.countryCodes ?: "",
    )