package m_rnd.keingeldfuerdiemensa.presentation.settings

import kotlinx.coroutines.flow.StateFlow
import m_rnd.keingeldfuerdiemensa.entities.Canteen

interface CanteenSettingsDelegate {

    val isSortEnabled: StateFlow<Boolean>
    val canteensFlow: StateFlow<CanteenList>

    fun deleteCanteen(canteen: Canteen)
    fun toggleSortMode()
    fun toggleCanteenVisibility(canteen: Canteen)
    fun moveCanteen(from: Int, to: Int)
}