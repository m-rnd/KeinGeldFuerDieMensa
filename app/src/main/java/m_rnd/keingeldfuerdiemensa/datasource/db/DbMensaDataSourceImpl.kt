package m_rnd.keingeldfuerdiemensa.datasource.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import m_rnd.keingeldfuerdiemensa.datasource.db.implementation.MensaDao
import m_rnd.keingeldfuerdiemensa.datasource.db.mapper.toDbEntity
import m_rnd.keingeldfuerdiemensa.datasource.db.mapper.toEntity
import m_rnd.keingeldfuerdiemensa.entities.Mensa
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import javax.inject.Inject


class DbMensaDataSourceImpl @Inject constructor(
    private val mensaDao: MensaDao,
) : DbMensaDataSource {
    override suspend fun insertMensa(mensa: Mensa) {
        mensaDao.insertMensa(mensa.toDbEntity())
    }

    override suspend fun insertMensas(mensas: List<Mensa>): List<Long> {
        return try {
            mensaDao.insertMensas(mensas.toDbEntity())
        } catch (e: Exception) {
            listOf()
        }
    }


    override fun getMensas(): Flow<AppResult<List<Mensa>>> {
        return mensaDao.getAllMensas().map {
            when {
                it.isEmpty() -> AppResult.Error(ErrorReason.Db.EmptyResult)
                else -> AppResult.Success(it.toEntity())
            }
        }
    }
}