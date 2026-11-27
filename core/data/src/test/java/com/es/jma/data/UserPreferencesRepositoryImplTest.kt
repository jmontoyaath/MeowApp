package com.es.jma.data

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.es.jma.data.preferences.ThemePreferences
import com.es.jma.data.preferences.UnitSystemPreferences
import com.es.jma.data.repository.UserPreferencesRepositoryImpl
import com.es.jma.model.ThemeConfigEnum
import com.es.jma.model.UnitSystemEnum
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

class UserPreferencesRepositoryImplTest {
    @get:Rule
    val tmp = TemporaryFolder()

    private fun TestScope.repository(): UserPreferencesRepositoryImpl {
        val dataStore = PreferenceDataStoreFactory.create(
            scope = backgroundScope,
            produceFile = { File(tmp.root, "test.preferences_pb") }
        )
        return UserPreferencesRepositoryImpl(
            ThemePreferences(dataStore),
            UnitSystemPreferences(dataStore)
        )
    }

    @Test
    fun `theme defaults to LIGHT`() = runTest {
        assertEquals(ThemeConfigEnum.LIGHT, repository().themeConfig.first())
    }

    @Test
    fun `setThemeConfig persists the new value`() = runTest {
        val repo = repository()
        repo.setThemeConfig(ThemeConfigEnum.DARK)
        assertEquals(ThemeConfigEnum.DARK, repo.themeConfig.first())
    }

    @Test
    fun `unit system defaults to METRICS and can change`() = runTest {
        val repo = repository()
        assertEquals(UnitSystemEnum.METRICS, repo.unitSystemConfig.first())
        repo.setUnitSystem(UnitSystemEnum.IMPERIAL)
        assertEquals(UnitSystemEnum.IMPERIAL, repo.unitSystemConfig.first())
    }
}