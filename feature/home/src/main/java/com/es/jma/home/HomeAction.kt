package com.es.jma.home

sealed interface HomeAction {
    data object Loading : HomeAction
    data object Error : HomeAction
}

