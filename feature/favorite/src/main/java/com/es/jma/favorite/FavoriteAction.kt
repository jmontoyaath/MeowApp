package com.es.jma.favorite

sealed interface FavoriteAction {
    data object ErrorDeletingFavorite: FavoriteAction
    data class ShowDetailModal(
        val catId: String,
        val urlImage: String,
        val breedId: String
    ): FavoriteAction
}