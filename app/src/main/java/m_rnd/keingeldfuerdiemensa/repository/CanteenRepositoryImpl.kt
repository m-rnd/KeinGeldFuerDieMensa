package m_rnd.keingeldfuerdiemensa.repository

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.datasource.api.OpenMensaDataSource
import m_rnd.keingeldfuerdiemensa.datasource.db.DbCanteenDataSource
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.util.*
import javax.inject.Inject

class CanteenRepositoryImpl @Inject constructor(
    private val openMensaDataSource: OpenMensaDataSource,
    private val dbCanteenDataSource: DbCanteenDataSource,
) : CanteenRepository {

    private fun groupSimilarMeals(meals: List<Meal>): List<Meal> {
        return meals.groupBy { it.notes.sorted() + it.category + it.prices }.mapNotNull {
            val groupedMeals = it.value
            val firstMeal = groupedMeals.firstOrNull()
            firstMeal?.let {
                Meal(
                    id = firstMeal.id,
                    names = groupedMeals.flatMap { it.names },
                    notes = firstMeal.notes,
                    prices = firstMeal.prices,
                    category = firstMeal.category
                )
            }
        }
    }

    override fun getCanteensWithMealsForDay(date: String): Flow<FlowState<List<Canteen>>> {
        return dbCanteenDataSource.getVisibleCanteens()
            .mapSuccessTo {
                val canteensWithMeals = it.map { canteen ->
                    when (val meals = openMensaDataSource.getMealsForCanteen(canteen.id, date)) {
                        is AppResult.Success -> canteen.copy(meals = groupSimilarMeals(meals.data))
                        is AppResult.Error -> {
                            // return the canteen with empty meals if there are no meals at the specific date
                            // if there is another error, cancel meal plan downloading
                            if (meals.reason is ErrorReason.Api.ErrorResponse.NotFound) {
                                canteen
                            } else {
                                return@mapSuccessTo FlowState.Error(meals.reason)
                            }
                        }
                    }
                }
                FlowState.Success(canteensWithMeals)
            }
    }

    override fun getCanteens(): Flow<FlowState<List<Canteen>>> {
        return dbCanteenDataSource.getCanteens()
    }

    override suspend fun getCanteenSearchResults(): AppResult<List<CanteenSearchResult>> {
        return openMensaDataSource.getCanteens()
    }

    override suspend fun saveCanteen(canteen: Canteen): AppResult<Unit> {
        return dbCanteenDataSource.insertCanteen(canteen)
    }

    override suspend fun deleteCanteen(canteen: Canteen): AppResult<Unit> {
        return dbCanteenDataSource.deleteCanteen(canteen)
    }

    override suspend fun setCanteenPriority(canteen: Canteen, priority: Int): AppResult<Unit> {
        return dbCanteenDataSource.setCanteenPriority(canteen, priority)
    }

    override suspend fun setCanteenVisible(canteen: Canteen, isVisible: Boolean): AppResult<Unit> {
        return dbCanteenDataSource.setCanteenVisible(canteen, isVisible)
    }
}