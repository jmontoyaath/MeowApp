package com.es.jma.model

data class CatInfo (
    val id: String,
    val url: String,
    val isFavorite: Boolean = false,
    val idBreed: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String
)