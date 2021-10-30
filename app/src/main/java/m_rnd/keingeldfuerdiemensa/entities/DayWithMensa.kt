package m_rnd.keingeldfuerdiemensa.entities

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult

data class DayWithMensas(
    val day: Long,
    val mensas: Flow<AppResult<List<Mensa>>>
) {
}