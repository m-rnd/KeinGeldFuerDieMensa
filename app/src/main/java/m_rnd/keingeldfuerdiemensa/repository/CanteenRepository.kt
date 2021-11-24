package m_rnd.keingeldfuerdiemensa.repository

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState

interface CanteenRepository {
    fun getCanteensWithMealsForDay(date: String): Flow<FlowState<List<Canteen>>>

    fun getCanteens(): Flow<FlowState<List<Canteen>>>

    suspend fun getCanteenSearchResults(): AppResult<List<CanteenSearchResult>>

    suspend fun saveCanteen(canteen: Canteen): AppResult<Unit>

    suspend fun deleteCanteen(canteen: Canteen): AppResult<Unit>

    suspend fun setCanteenPriority(canteen: Canteen, priority: Int): AppResult<Unit>

    suspend fun setCanteenVisible(canteen: Canteen, isVisible: Boolean): AppResult<Unit>
}