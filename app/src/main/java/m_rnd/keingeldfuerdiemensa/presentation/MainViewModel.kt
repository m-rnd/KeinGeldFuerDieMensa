package m_rnd.keingeldfuerdiemensa.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import m_rnd.keingeldfuerdiemensa.entities.DayWithCanteens
import m_rnd.keingeldfuerdiemensa.usecase.GetCanteensWithMealsUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

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
}