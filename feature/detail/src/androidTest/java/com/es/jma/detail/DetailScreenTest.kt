package com.es.jma.detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.es.jma.testing.fakeBreed
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailScreenTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    private fun fakeViewModel(state: DetailUiState): DetailViewModel {
        val stateFlow = MutableStateFlow(state)
        return mockk(relaxed = true) {
            every { uiState } returns stateFlow
            every { action } returns emptyFlow()
        }
    }

    @Test
    fun content_showsBreedInformation() {
        composeTestRule.setContent {
            DetailScreenContent(data = fakeBreed, catImage = "")
        }

        composeTestRule.onNodeWithText(fakeBreed.name!!).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeBreed.origin!!).assertIsDisplayed()
    }

    @Test
    fun content_withNullData_doesNotShowWikiButton() {
        composeTestRule.setContent {
            DetailScreenContent(data = null, catImage = null)
        }

        composeTestRule
            .onNodeWithText("Go kitten wiki")
            .assertDoesNotExist()
    }

    @Test
    fun screen_success_showsContent() {
        composeTestRule.setContent {
            DetailScreen(
                idBreed = "1",
                imageCat = "",
                viewModel = fakeViewModel(DetailUiState.Success(fakeBreed)),
                onDismiss = {}
            )
        }

        composeTestRule.onNodeWithText("Mayillo").assertIsDisplayed()
    }

    @Test
    fun screen_loading_doesNotShowContent() {
        composeTestRule.setContent {
            DetailScreen(
                idBreed = "1",
                imageCat = "",
                viewModel = fakeViewModel(DetailUiState.Loading),
                onDismiss = {}
            )
        }

        composeTestRule.onNodeWithText("Mayillo").assertDoesNotExist()
    }

    @Test
    fun screen_error_doesNotShowContent() {
        composeTestRule.setContent {
            DetailScreen(
                idBreed = "1",
                imageCat = "",
                viewModel = fakeViewModel(DetailUiState.Error),
                onDismiss = {}
            )
        }

        composeTestRule.onNodeWithText("Mayillo").assertDoesNotExist()
    }
}