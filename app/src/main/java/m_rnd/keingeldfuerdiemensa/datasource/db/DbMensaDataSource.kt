package m_rnd.keingeldfuerdiemensa.datasource.db

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.Mensa
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult

interface DbMensaDataSource {
    suspend fun insertMensa(mensa: Mensa)

    suspend fun insertMensas(mensas: List<Mensa>): List<Long>

    fun getMensas(): Flow<AppResult<List<Mensa>>>
}