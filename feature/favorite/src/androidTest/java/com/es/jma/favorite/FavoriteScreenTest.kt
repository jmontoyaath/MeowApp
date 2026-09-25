package com.es.jma.favorite

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.es.jma.model.CatInfo
import com.es.jma.testing.fakeCat
import com.es.jma.testing.fakeCatTwo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteScreenTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun favoriteScreenEmpty_showsEmptyMessage() {
        composeTestRule.setContent {
            FavoriteScreenEmpty()
        }

        composeTestRule
            .onNodeWithText("In the Home page you can mark as favorites to show here")
            .assertIsDisplayed()
    }

    @Test
    fun favoriteScreenContent_displaysCatItems_andTriggersClick() {
        var clickedCat: CatInfo? = null
        val fakeCats = listOf(
            fakeCat,
            fakeCatTwo
        )

        composeTestRule.setContent {
            FavoriteScreenContent(
                cats = fakeCats,
                onCatClicked = { cat -> clickedCat = cat },
                onDeleteClicked = {}
            )
        }

        composeTestRule
            .onNodeWithText(fakeCat.name, substring = true, ignoreCase = true)
            .assertIsDisplayed()
            .performClick()

        assert(clickedCat?.id == "1")
    }
}