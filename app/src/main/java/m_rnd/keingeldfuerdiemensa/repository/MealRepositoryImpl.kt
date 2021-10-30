package m_rnd.keingeldfuerdiemensa.repository

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.datasource.MealDataSource
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.extensions.filterSuccess
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSource
): MealRepository {

    override fun getMeals(canteenId: Int, date: String): Flow<AppResult<List<Meal>>> {
        return mealDataSource.getMeals(canteenId, date).filterSuccess { meal ->
            !meal.category.contains("Smoothie") &&
                    !meal.category.contains("Pasta") &&
                    !meal.category.contains("Pizza")
        }
    }
}

