package com.es.jma.model

data class Breed (
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val referenceImage: String? = null
)