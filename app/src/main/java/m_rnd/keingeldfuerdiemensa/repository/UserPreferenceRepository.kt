package m_rnd.keingeldfuerdiemensa.repository

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.datasource.datastore.UserPreferences
import m_rnd.keingeldfuerdiemensa.entities.ThemeMode
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState

interface UserPreferenceRepository {
    val userPreferences: Flow<FlowState<UserPreferences>>

    val dynamicColorsEnabled: Flow<Boolean>
    val themeMode: Flow<ThemeMode>

    suspend fun setDynamicColorsEnabled(enabled: Boolean): AppResult<Unit>
    suspend fun setThemeMode(themeMode: ThemeMode): AppResult<Unit>
}