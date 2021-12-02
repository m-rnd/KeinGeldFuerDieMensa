package m_rnd.keingeldfuerdiemensa.datasource.db.implementation

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.datasource.db.model.DbCanteen

@Dao
interface CanteenDao {

    @Insert
    suspend fun insertCanteen(canteen: DbCanteen): Long

    @Delete
    suspend fun deleteCanteen(canteen: DbCanteen): Int

    @Query("UPDATE canteen SET priority = :priority WHERE id = :canteenId")
    suspend fun setCanteenPriority(canteenId: Int, priority: Int): Int

    @Query("UPDATE canteen SET isVisible = :isVisible WHERE id = :canteenId")
    suspend fun setCanteenVisible(canteenId: Int, isVisible: Boolean): Int

    @Query("SELECT * FROM canteen ORDER BY priority")
    fun getAllCanteens(): Flow<List<DbCanteen>>

    @Query("SELECT * FROM canteen WHERE isVisible = 1 ORDER BY priority")
    fun getAllVisibleCanteens(): Flow<List<DbCanteen>>
}