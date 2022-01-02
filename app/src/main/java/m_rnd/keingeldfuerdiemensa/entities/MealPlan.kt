package m_rnd.keingeldfuerdiemensa.entities

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import java.io.Serializable

data class MealPlan(
    val day: Long,
    val canteens: Flow<FlowState<List<Canteen>>>
) : Serializable