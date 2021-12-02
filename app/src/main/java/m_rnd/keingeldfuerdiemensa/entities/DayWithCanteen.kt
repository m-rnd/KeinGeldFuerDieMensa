package m_rnd.keingeldfuerdiemensa.entities

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import java.io.Serializable

data class DayWithCanteens(
    val day: Long,
    val canteens: Flow<FlowState<List<Canteen>>>
): Serializable