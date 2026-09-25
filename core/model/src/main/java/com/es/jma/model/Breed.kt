package com.es.jma.model

data class Breed (
    val id: String,
    val name: String? = null,
    val temperament: String? = null,
    val origin: String? = null,
    val codeCountry: String? = null,
    val description: String? = null,
    val lifeSpan: String? = null,
    val breedGroup: String? = null,
    val history: String? = null,
    val referenceImage: String? = null,
    val wikipediaUrl: String? = null,
    val weight: Metrics? = null,
    val height: Metrics? = null,
)

data class Metrics(
    val imperial: String? = null,
    val metric: String? = null,
)