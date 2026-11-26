package com.es.jma.settings

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.es.jma.model.ThemeConfigEnum
import com.es.jma.model.UnitSystemEnum
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val isRadioButton =
        SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.RadioButton)

    private val themeCount = ThemeConfigEnum.entries.size
    private val unitSystemCount = UnitSystemEnum.entries.size

    private fun radioAt(index: Int) =
        composeTestRule.onAllNodes(isRadioButton)[index]

    private fun themeRadio(theme: ThemeConfigEnum) = radioAt(theme.ordinal)

    private fun unitSystemRadio(unitSystem: UnitSystemEnum) =
        radioAt(themeCount + unitSystem.ordinal)

    private fun setContent(
        theme: ThemeConfigEnum = ThemeConfigEnum.LIGHT,
        unitSystem: UnitSystemEnum = UnitSystemEnum.METRICS,
        onClickTheme: (ThemeConfigEnum) -> Unit = {},
        onClickUnitSystem: (UnitSystemEnum) -> Unit = {},
    ) {
        composeTestRule.setContent {
            SettingsScreenContent(
                theme = theme,
                unitSystem = unitSystem,
                onClickTheme = onClickTheme,
                onClickUnitSystem = onClickUnitSystem,
            )
        }
    }

    @Test
    fun showsSectionTitles() {
        setContent()

        composeTestRule.onNodeWithText(context.getString(R.string.theme)).assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.unit_system)).assertIsDisplayed()
    }

    @Test
    fun showsLabelForEveryOption() {
        setContent()

        ThemeConfigEnum.entries.forEach {
            composeTestRule.onNodeWithText(context.getString(it.toLabelRes())).assertIsDisplayed()
        }
        UnitSystemEnum.entries.forEach {
            composeTestRule.onNodeWithText(context.getString(it.toLabelRes())).assertIsDisplayed()
        }
    }

    @Test
    fun showsOneRadioButtonPerOption() {
        setContent()

        composeTestRule.onAllNodes(isRadioButton).assertCountEquals(themeCount + unitSystemCount)
    }

    @Test
    fun onlyCurrentThemeIsSelected() {
        setContent(theme = ThemeConfigEnum.DARK)

        themeRadio(ThemeConfigEnum.DARK).assertIsSelected()
        themeRadio(ThemeConfigEnum.LIGHT).assertIsNotSelected()
    }

    @Test
    fun onlyCurrentUnitSystemIsSelected() {
        setContent(unitSystem = UnitSystemEnum.IMPERIAL)

        unitSystemRadio(UnitSystemEnum.IMPERIAL).assertIsSelected()
        unitSystemRadio(UnitSystemEnum.METRICS).assertIsNotSelected()
    }

    @Test
    fun clickingThemeOptionInvokesCallbackWithThatOption() {
        var clickedTheme: ThemeConfigEnum? = null
        var clickedUnitSystem: UnitSystemEnum? = null
        setContent(
            theme = ThemeConfigEnum.LIGHT,
            onClickTheme = { clickedTheme = it },
            onClickUnitSystem = { clickedUnitSystem = it },
        )

        themeRadio(ThemeConfigEnum.DARK).performClick()

        assertEquals(ThemeConfigEnum.DARK, clickedTheme)
        assertNull(clickedUnitSystem)
    }

    @Test
    fun clickingUnitSystemOptionInvokesCallbackWithThatOption() {
        var clickedTheme: ThemeConfigEnum? = null
        var clickedUnitSystem: UnitSystemEnum? = null
        setContent(
            unitSystem = UnitSystemEnum.METRICS,
            onClickTheme = { clickedTheme = it },
            onClickUnitSystem = { clickedUnitSystem = it },
        )

        unitSystemRadio(UnitSystemEnum.IMPERIAL).performClick()

        assertEquals(UnitSystemEnum.IMPERIAL, clickedUnitSystem)
        assertNull(clickedTheme)
    }

    @Test
    fun labelsAreNotDuplicated() {
        setContent()

        composeTestRule
            .onAllNodesWithText(context.getString(ThemeConfigEnum.DARK.toLabelRes()))
            .assertCountEquals(1)
    }
}