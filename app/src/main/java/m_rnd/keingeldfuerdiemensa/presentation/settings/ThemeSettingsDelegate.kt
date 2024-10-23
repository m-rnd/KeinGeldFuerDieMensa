package m_rnd.keingeldfuerdiemensa.presentation.settings

import kotlinx.coroutines.flow.StateFlow
import m_rnd.keingeldfuerdiemensa.entities.ThemeMode


interface ThemeSettingsDelegate {
    val themeMode: StateFlow<ThemeMode>
    val useDynamicColors: StateFlow<Boolean>

    fun onThemeModeChange(themeMode: ThemeMode)
    fun onDynamicColorSelect(useDynamicColor: Boolean)

}