package com.es.jma.settings

import com.es.jma.data.repository.UserPreferencesRepository
import com.es.jma.domain.usecase.UpdateThemeAppUseCase
import com.es.jma.domain.usecase.UpdateUnitSystemAppUseCase
import com.es.jma.model.ThemeConfigEnum
import com.es.jma.model.UnitSystemEnum
import com.es.jma.testing.MainDispatcherRule
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val updateThemeAppUseCase: UpdateThemeAppUseCase = mockk(relaxed = true)
    private val updateUnitSystemAppUseCase: UpdateUnitSystemAppUseCase = mockk(relaxed = true)
    private val userPreferencesRepository: UserPreferencesRepository = mockk()

    private val themeFlow = MutableSharedFlow<ThemeConfigEnum>(replay = 1)
    private val unitSystemFlow = MutableSharedFlow<UnitSystemEnum>(replay = 1)

    @Before
    fun setUp() {
        every { userPreferencesRepository.themeConfig } returns themeFlow
        every { userPreferencesRepository.unitSystemConfig } returns unitSystemFlow
    }

    private fun createViewModel() = SettingsViewModel(
        updateThemeAppUseCase = updateThemeAppUseCase,
        updateUnitSystemAppUseCase = updateUnitSystemAppUseCase,
        userPreferencesRepository = userPreferencesRepository,
    )

    @Test
    fun `initial state is Loading when preferences have not emitted`() = runTest {
        val viewModel = createViewModel()

        assertEquals(SettingsUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `state stays Loading when only theme has emitted`() = runTest {
        val viewModel = createViewModel()

        themeFlow.emit(ThemeConfigEnum.DARK)

        assertEquals(SettingsUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `state stays Loading when only unit system has emitted`() = runTest {
        val viewModel = createViewModel()

        unitSystemFlow.emit(UnitSystemEnum.IMPERIAL)

        assertEquals(SettingsUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `state is Success when both preferences emit`() = runTest {
        val viewModel = createViewModel()

        themeFlow.emit(ThemeConfigEnum.DARK)
        unitSystemFlow.emit(UnitSystemEnum.IMPERIAL)

        assertEquals(
            SettingsUiState.Success(
                theme = ThemeConfigEnum.DARK,
                unitSystem = UnitSystemEnum.IMPERIAL,
            ),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `state updates when theme changes in repository`() = runTest {
        val viewModel = createViewModel()
        themeFlow.emit(ThemeConfigEnum.LIGHT)
        unitSystemFlow.emit(UnitSystemEnum.METRICS)

        themeFlow.emit(ThemeConfigEnum.DARK)

        assertEquals(
            SettingsUiState.Success(
                theme = ThemeConfigEnum.DARK,
                unitSystem = UnitSystemEnum.METRICS,
            ),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `state updates when unit system changes in repository`() = runTest {
        val viewModel = createViewModel()
        themeFlow.emit(ThemeConfigEnum.LIGHT)
        unitSystemFlow.emit(UnitSystemEnum.METRICS)

        unitSystemFlow.emit(UnitSystemEnum.IMPERIAL)

        assertEquals(
            SettingsUiState.Success(
                theme = ThemeConfigEnum.LIGHT,
                unitSystem = UnitSystemEnum.IMPERIAL,
            ),
            viewModel.uiState.value,
        )
    }

    @Test
    fun `init does not call any update use case`() = runTest {
        createViewModel()

        coVerify(exactly = 0) { updateThemeAppUseCase(any()) }
        coVerify(exactly = 0) { updateUnitSystemAppUseCase(any()) }
    }

    @Test
    fun `updateThemeConfig delegates every option to use case`() = runTest {
        val viewModel = createViewModel()

        ThemeConfigEnum.entries.forEach { theme ->
            viewModel.updateThemeConfig(theme)

            coVerify(exactly = 1) { updateThemeAppUseCase(theme) }
        }
        coVerify(exactly = 0) { updateUnitSystemAppUseCase(any()) }
    }

    @Test
    fun `updateUnitSystem delegates every option to use case`() = runTest {
        val viewModel = createViewModel()

        UnitSystemEnum.entries.forEach { unitSystem ->
            viewModel.updateUnitSystem(unitSystem)

            coVerify(exactly = 1) { updateUnitSystemAppUseCase(unitSystem) }
        }
        coVerify(exactly = 0) { updateThemeAppUseCase(any()) }
    }
}