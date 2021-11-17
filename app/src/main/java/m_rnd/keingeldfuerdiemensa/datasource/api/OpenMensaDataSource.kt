package m_rnd.keingeldfuerdiemensa.datasource.api

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult

interface OpenMensaDataSource {
    fun getMeals(canteenId: Int, date: String): Flow<AppResult<List<Meal>>>

    suspend fun getMealsAsync(canteenId: Int, date: String): AppResult<List<Meal>>

    suspend fun getCanteensAsync(): AppResult<List<CanteenSearchResult>>
}