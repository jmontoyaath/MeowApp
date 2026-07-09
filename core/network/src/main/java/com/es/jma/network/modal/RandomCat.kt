package com.es.jma.network.modal

import com.es.jma.model.CatInfo
import kotlinx.serialization.Serializable

/**
 * Network representation of [com.es.jma.model.CatInfo]
 */
@Serializable
data class RandomCat(
    val id: String,
    val url: String
)

fun RandomCat.asExternalModel(): CatInfo =
    CatInfo(
        id = id,
        url = url
    )