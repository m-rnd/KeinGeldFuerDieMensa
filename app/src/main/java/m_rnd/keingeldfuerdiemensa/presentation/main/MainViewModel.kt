package m_rnd.keingeldfuerdiemensa.presentation.main

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.common.DAY_IN_MS
import m_rnd.keingeldfuerdiemensa.entities.MealPlan
import m_rnd.keingeldfuerdiemensa.entities.util.NavigationTarget
import m_rnd.keingeldfuerdiemensa.ui.navigation.Navigator
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteensWithMealsUseCase
import javax.inject.Inject

enum class MainMenuItem(@StringRes val displayName: Int) {
    CANTEEN_SETTINGS(R.string.canteen_settings_title),
    ABOUT(R.string.about_title)
}


@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    @Inject
    lateinit var getCanteensWithMealsUseCase: GetCanteensWithMealsUseCase

    private fun getCanteensForDay(timestamp: Long) = getCanteensWithMealsUseCase(timestamp)

    fun getMealPlans(): List<MealPlan> {
        val currentTs = System.currentTimeMillis()
        val plans = mutableListOf<MealPlan>()
        for (ts in currentTs..currentTs + 7 * DAY_IN_MS step DAY_IN_MS) {
            plans.add(
                MealPlan(
                    ts,
                    getCanteensForDay(ts)
                )
            )
        }
        return plans
    }

    fun navigateToSettings(mockMealPlan: MealPlan) {
        navigator.navigateTo(NavigationTarget.Settings)
    }

    fun navigateToAddCanteenScreen() {
        navigator.navigateTo(NavigationTarget.AddCanteen)
    }
}