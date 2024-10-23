package m_rnd.keingeldfuerdiemensa.repository

import m_rnd.keingeldfuerdiemensa.datasource.datastore.USER_PREFERENCE_DEFAULT_THEME_MODE
import m_rnd.keingeldfuerdiemensa.datasource.datastore.USER_PREFERENCE_DEFAULT_USE_DYNAMIC_COLORS
import m_rnd.keingeldfuerdiemensa.datasource.datastore.UserPreferencesDataSource
import m_rnd.keingeldfuerdiemensa.entities.ThemeMode
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.successOr
import javax.inject.Inject

class UserPreferenceRepositoryImpl @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource
) : UserPreferenceRepository {
    override val userPreferences = userPreferencesDataSource.userPreferences

    override val dynamicColorsEnabled = userPreferences.successOr(
        USER_PREFERENCE_DEFAULT_USE_DYNAMIC_COLORS
    ) {
        it.useDynamicColors
    }
    override val themeMode = userPreferences.successOr(USER_PREFERENCE_DEFAULT_THEME_MODE) {
        ThemeMode.entries[it.themeMode]
    }

    override suspend fun setDynamicColorsEnabled(enabled: Boolean): AppResult<Unit> {
        return userPreferencesDataSource.setDynamicColorsEnabled(enabled)
    }

    override suspend fun setThemeMode(themeMode: ThemeMode): AppResult<Unit> {
        return userPreferencesDataSource.setThemeMode(themeMode)
    }
}