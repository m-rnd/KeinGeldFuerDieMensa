package m_rnd.keingeldfuerdiemensa.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import m_rnd.keingeldfuerdiemensa.entities.util.NavigationTarget
import m_rnd.keingeldfuerdiemensa.ui.navigation.Navigator
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    canteenSettingsDelegate: CanteenSettingsDelegateImpl,
    themeSettingsDelegate: ThemeSettingsDelegateImpl,
    private val navigator: Navigator,
) : ViewModel(),
    CanteenSettingsDelegate by canteenSettingsDelegate,
    ThemeSettingsDelegate by themeSettingsDelegate {

    init {
        canteenSettingsDelegate(viewModelScope)
        themeSettingsDelegate(viewModelScope)
    }

    fun navigateToAddCanteenScreen() {
        navigator.navigateTo(NavigationTarget.AddCanteen)
    }

    fun navigateUp() {
        navigator.navigateUp()
    }

    fun navigateToAbout() {
        navigator.navigateTo(NavigationTarget.About)
    }
}