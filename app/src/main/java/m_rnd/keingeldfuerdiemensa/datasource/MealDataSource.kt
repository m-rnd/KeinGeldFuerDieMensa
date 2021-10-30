package m_rnd.keingeldfuerdiemensa.datasource

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult

interface MealDataSource {
    fun getMeals(canteenId: Int, date: String): Flow<AppResult<List<Meal>>>
}