package m_rnd.keingeldfuerdiemensa.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteensUseCase
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getCanteensUseCase: GetCanteensUseCase
) : ViewModel() {

    fun getCanteens() = getCanteensUseCase(Unit)
}