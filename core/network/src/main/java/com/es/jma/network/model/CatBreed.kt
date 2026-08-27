package com.es.jma.network.model

import com.es.jma.model.Breed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Network representation of [CatBreed]
 */
@Serializable
data class CatBreed (
    val id: String,
    val name: String? = null,
    val temperament: String? = null,
    val origin: String? = null,
    @SerialName("country_code") val countryCodes: String? = null,
    val description: String? = null,
    val referenceImage: String? = null,
    @SerialName("wikipedia_url") val wikipediaUrl: String? = null,
)

fun CatBreed.asExternalModel() : Breed = Breed(
    id = id,
    name = name,
    temperament = temperament,
    origin = origin,
    codeCountry = countryCodes,
    description = description,
    referenceImage = referenceImage,
    wikipediaUrl = wikipediaUrl
)