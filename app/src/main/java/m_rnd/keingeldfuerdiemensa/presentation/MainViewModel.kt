package m_rnd.keingeldfuerdiemensa.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import m_rnd.keingeldfuerdiemensa.entities.DayWithCanteens
import m_rnd.keingeldfuerdiemensa.entities.util.NavigationTarget
import m_rnd.keingeldfuerdiemensa.ui.navigation.Navigator
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteensWithMealsUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    @Inject
    lateinit var getCanteensWithMealsUseCase: GetCanteensWithMealsUseCase

    private fun getCanteensForDay(timestamp: Long) = getCanteensWithMealsUseCase(timestamp)

    fun getCanteensForNextDays(): List<DayWithCanteens> {
        val dayInMs: Long = 1000 * 60 * 60 * 24
        val currentTs = System.currentTimeMillis()
        val canteens = mutableListOf<DayWithCanteens>()
        for (ts in currentTs..currentTs + 7 * dayInMs step dayInMs) {
            canteens.add(
                DayWithCanteens(
                    ts,
                    getCanteensForDay(ts)
                )
            )
        }
        return canteens
    }

    fun navigateToSettingsScreen() {
        navigator.navigateTo(NavigationTarget.Settings.Canteen)
    }

    fun navigateToAddCanteenScreen() {
        navigator.navigateTo(NavigationTarget.AddCanteen)
    }
}