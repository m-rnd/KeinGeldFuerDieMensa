package m_rnd.keingeldfuerdiemensa.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import m_rnd.keingeldfuerdiemensa.common.DAY_IN_MS
import m_rnd.keingeldfuerdiemensa.entities.MealPlan
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

    fun getMealPlans(): List<MealPlan> {
        val currentTs = System.currentTimeMillis()
        val canteens = mutableListOf<MealPlan>()
        for (ts in currentTs..currentTs + 7 * DAY_IN_MS step DAY_IN_MS) {
            canteens.add(
                MealPlan(
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