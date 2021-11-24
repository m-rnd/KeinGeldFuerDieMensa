package m_rnd.keingeldfuerdiemensa.datasource.db

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState

interface DbCanteenDataSource {

    fun getCanteens(): Flow<FlowState<List<Canteen>>>

    fun getVisibleCanteens(): Flow<FlowState<List<Canteen>>>

    suspend fun insertCanteen(canteen: Canteen): AppResult<Unit>

    suspend fun deleteCanteen(canteen: Canteen): AppResult<Unit>

    suspend fun setCanteenPriority(canteen: Canteen, priority: Int): AppResult<Unit>

    suspend fun setCanteenVisible(canteen: Canteen, isVisible: Boolean): AppResult<Unit>
}

