package m_rnd.keingeldfuerdiemensa.datasource.db

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult

interface DbCanteenDataSource {
    suspend fun insertCanteen(canteen: Canteen): Long

    suspend fun insertCanteens(canteens: List<Canteen>): List<Long>

    fun getCanteens(): Flow<AppResult<List<Canteen>>>
}