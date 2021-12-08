package m_rnd.keingeldfuerdiemensa.entities.mock

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.MealPlan
import m_rnd.keingeldfuerdiemensa.entities.PriceInfo
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState

object PreviewEntity {
    fun MealPlanMock(
        day: Long = System.currentTimeMillis(),
        canteens: Flow<FlowState<List<Canteen>>> = flowOf(FlowState.Success(listOf(CanteenMock())))
    ) = MealPlan(day, canteens)

    fun CanteenMock(
        id: Int = 0,
        name: String = "Mensa am Park",
        meals: List<Meal> = listOf(MealMock()),
        isVisible: Boolean = true,
        priority: Int = 0
    ) = Canteen(id, name, meals, isVisible, priority)


    fun MealMock(
        id: Int = 0,
        name: String = "Currywurst",
        notes: List<String> = listOf("mit Pommes", "Schwein"),
        prices: PriceInfo = PriceInfo(
            students = 2.50f,
            employees = 2.50f,
            pupils = 2.70f,
            others = 3.10f
        ),
        category: String = "Schneller Teller"
    ) = Meal(id, name, notes, prices, category)
}

