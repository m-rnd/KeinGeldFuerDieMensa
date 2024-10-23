package m_rnd.keingeldfuerdiemensa.presentation.settings

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import m_rnd.keingeldfuerdiemensa.datasource.datastore.USER_PREFERENCE_DEFAULT_THEME_MODE
import m_rnd.keingeldfuerdiemensa.datasource.datastore.USER_PREFERENCE_DEFAULT_USE_DYNAMIC_COLORS
import m_rnd.keingeldfuerdiemensa.entities.ThemeMode
import m_rnd.keingeldfuerdiemensa.presentation.ViewModelDelegate
import m_rnd.keingeldfuerdiemensa.repository.UserPreferenceRepository
import javax.inject.Inject


class ThemeSettingsDelegateImpl @Inject constructor(
    private val userPreferenceRepository: UserPreferenceRepository
) : ThemeSettingsDelegate, ViewModelDelegate() {

    override val themeMode: StateFlow<ThemeMode> by lazy {
        userPreferenceRepository.themeMode.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            USER_PREFERENCE_DEFAULT_THEME_MODE
        )
    }
    override val useDynamicColors: StateFlow<Boolean> by lazy {
        userPreferenceRepository.dynamicColorsEnabled.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            USER_PREFERENCE_DEFAULT_USE_DYNAMIC_COLORS
        )
    }

    override fun onThemeModeChange(themeMode: ThemeMode) {
        viewModelScope.launch {
            userPreferenceRepository.setThemeMode(themeMode)
        }
    }

    override fun onDynamicColorSelect(useDynamicColor: Boolean) {
        viewModelScope.launch {
            userPreferenceRepository.setDynamicColorsEnabled(useDynamicColor)
        }
    }
}