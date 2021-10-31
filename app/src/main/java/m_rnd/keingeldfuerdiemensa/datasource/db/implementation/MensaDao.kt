package m_rnd.keingeldfuerdiemensa.datasource.db.implementation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.datasource.db.model.DbMensa

@Dao
interface MensaDao {

    @Insert
    fun insertMensa(mensa: DbMensa): Long

    @Insert
    fun insertMensas(mensas: List<DbMensa>): List<Long>

    @Query("SELECT * FROM mensa")
    fun getAllMensas(): Flow<List<DbMensa>>
}