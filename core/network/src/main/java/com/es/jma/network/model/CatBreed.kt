package com.es.jma.network.model

import com.es.jma.model.Breed
import com.es.jma.model.UnitSystem
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
    @SerialName("life_span") val lifeSpan: String? = null,
    val origin: String? = null,
    @SerialName("country_code") val countryCodes: String? = null,
    val description: String? = null,
    @SerialName("wikipedia_url") val wikipediaUrl: String? = null,
    @SerialName("breed_group") val breedGroup: String? = null,
    val history: String? = null,
    val weight: UnitSystemResponse? = null,
    val height: UnitSystemResponse? = null,
)

@Serializable
data class UnitSystemResponse(
    val imperial: String? = null,
    val metric: String? = null,
)

fun UnitSystemResponse.asExternalModel() : UnitSystem = UnitSystem(
    imperial = imperial,
    metric = metric
)

fun CatBreed.asExternalModel() : Breed = Breed(
    id = id,
    name = name,
    temperament = temperament,
    origin = origin,
    codeCountry = countryCodes,
    description = description,
    wikipediaUrl = wikipediaUrl,
    lifeSpan = lifeSpan,
    breedGroup = breedGroup,
    history = history,
    weight = weight?.asExternalModel(),
    height = height?.asExternalModel()
)