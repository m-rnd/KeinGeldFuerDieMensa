package m_rnd.keingeldfuerdiemensa.repository

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult

interface MealRepository {
    fun getMeals(canteenId: Int, date: String): Flow<AppResult<List<Meal>>>
}