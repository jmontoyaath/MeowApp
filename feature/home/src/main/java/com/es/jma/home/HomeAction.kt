package com.es.jma.home

sealed interface HomeAction {
    data object ShowErrorAddFavorite : HomeAction
    data class NavigateToDetail(
        val catId: String,
        val urlImage: String,
        val breedId: String
    ) : HomeAction
}

