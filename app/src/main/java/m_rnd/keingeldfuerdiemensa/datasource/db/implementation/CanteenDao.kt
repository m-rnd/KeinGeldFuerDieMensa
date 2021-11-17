package m_rnd.keingeldfuerdiemensa.datasource.db.implementation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.datasource.db.model.DbCanteen

@Dao
interface CanteenDao {

    @Insert
    suspend fun insertCanteen(canteen: DbCanteen): Long

    @Insert
    suspend fun insertCanteens(canteens: List<DbCanteen>): List<Long>

    @Query("SELECT * FROM canteen")
    fun getAllCanteens(): Flow<List<DbCanteen>>
}