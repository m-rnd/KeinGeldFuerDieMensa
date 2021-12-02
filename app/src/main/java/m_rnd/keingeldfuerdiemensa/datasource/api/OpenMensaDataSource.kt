package m_rnd.keingeldfuerdiemensa.datasource.api

import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult

interface OpenMensaDataSource {
    suspend fun getMealsForCanteen(canteenId: Int, date: String): AppResult<List<Meal>>

    suspend fun getCanteens(): AppResult<List<CanteenSearchResult>>
}