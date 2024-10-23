package m_rnd.keingeldfuerdiemensa.datasource.datastore

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.ThemeMode
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState

// set this to false as a fallback to devices that don't support dynamic color
const val USER_PREFERENCE_DEFAULT_USE_DYNAMIC_COLORS = false
val USER_PREFERENCE_DEFAULT_THEME_MODE = ThemeMode.SYSTEM

interface UserPreferencesDataSource {
    val userPreferences: Flow<FlowState<UserPreferences>>
    suspend fun setThemeMode(themeMode: ThemeMode): AppResult<Unit>
    suspend fun setDynamicColorsEnabled(enabled: Boolean): AppResult<Unit>
}