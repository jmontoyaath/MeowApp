package com.es.jma.model

data class Breed (
    val id: String,
    val name: String? = null,
    val temperament: String? = null,
    val origin: String? = null,
    val codeCountry: String? = null,
    val description: String? = null,
    val referenceImage: String? = null,
    val wikipediaUrl: String? = null
)