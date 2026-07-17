package com.es.jma.favorite

interface FavoriteAction {
    data object ShowErrorFavorites: FavoriteAction
    data class ShowDetailModal(
        val catId: String,
        val urlImage: String,
        val breedId: String
    ): FavoriteAction
}