package com.es.jma.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.es.jma.model.CatInfo

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.action.collect { action ->
            when(action) {
                else -> Unit
            }
        }
    }

    when {
        state.loading -> HomeScreenLoading()
        state.showError -> HomeScreenError()
        state.data != null -> {
            HomeScreenContent(
                catList = state.data?.catList ?: listOf()
            )
        }
        else -> Unit
    }
}

@Composable
fun HomeScreenContent(
    catList: List<CatInfo> = listOf()
) {
    if (catList.isNotEmpty()) {
        Text(text = "Look! There is a cat!")
    }
}

@Composable
fun HomeScreenError() {
    Text(text = "There is no cat in the bag")
}

@Composable
fun HomeScreenLoading() {
    Text(text = "Searching for cats")
}