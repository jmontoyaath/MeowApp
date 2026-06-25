package com.es.jma.network.modal

import com.es.jma.model.Breed
import kotlinx.serialization.Serializable

/**
 * Network representation of [CatBreed]
 */
@Serializable
data class CatBreed (
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val referenceImage: String
)

fun CatBreed.asExternalModel() : Breed = Breed(
    id = id,
    name = name,
    temperament = temperament,
    origin = origin,
    description = description,
    referenceImage = referenceImage
)