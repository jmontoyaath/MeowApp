package com.es.jma.model

data class CatInfo (
    val id: String,
    val url: String,
    val isFavorite: Boolean = false,
    val breed: List<Breed>?
)