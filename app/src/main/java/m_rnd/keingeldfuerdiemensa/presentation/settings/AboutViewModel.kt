package m_rnd.keingeldfuerdiemensa.presentation.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import m_rnd.keingeldfuerdiemensa.ui.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {

    fun navigateUp() {
        navigator.navigateUp()
    }
}