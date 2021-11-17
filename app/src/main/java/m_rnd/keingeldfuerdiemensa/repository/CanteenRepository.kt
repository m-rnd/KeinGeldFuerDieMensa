package m_rnd.keingeldfuerdiemensa.repository

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult

interface CanteenRepository {
    fun getCanteensWithMealsForDay(date: String): Flow<AppResult<List<Canteen>>>
    fun getCanteens(): Flow<AppResult<List<Canteen>>>
    suspend fun getCanteenSearchResults(): AppResult<List<CanteenSearchResult>>
    suspend fun saveCanteen(canteen: Canteen): AppResult<Unit>
}