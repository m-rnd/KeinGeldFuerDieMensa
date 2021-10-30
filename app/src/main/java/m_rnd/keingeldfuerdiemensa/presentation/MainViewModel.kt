package m_rnd.keingeldfuerdiemensa.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import m_rnd.keingeldfuerdiemensa.entities.DayWithMensas
import m_rnd.keingeldfuerdiemensa.usecase.GetMensaUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var getMensaUseCase: GetMensaUseCase


    private fun getMensasForDay(timestamp: Long) = getMensaUseCase(timestamp)

    fun getMensasForNextDays(): List<DayWithMensas> {
        val dayInMs: Long = 1000 * 60 * 60 * 24
        val currentTs = System.currentTimeMillis()
        val mensas = mutableListOf<DayWithMensas>()
        for (ts in currentTs..currentTs + 7 * dayInMs step dayInMs) {
            mensas.add(
                DayWithMensas(
                    ts,
                    getMensasForDay(ts)
                )
            )
        }
        return mensas
    }
}