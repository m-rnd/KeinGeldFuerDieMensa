package m_rnd.keingeldfuerdiemensa.datasource.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import m_rnd.keingeldfuerdiemensa.datasource.db.implementation.CanteenDao
import m_rnd.keingeldfuerdiemensa.datasource.db.mapper.toDbEntity
import m_rnd.keingeldfuerdiemensa.datasource.db.mapper.toEntity
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import javax.inject.Inject


class DbCanteenDataSourceImpl @Inject constructor(
    private val canteenDao: CanteenDao,
) : DbCanteenDataSource {
    override suspend fun insertCanteen(canteen: Canteen): Long {
        return canteenDao.insertCanteen(canteen.toDbEntity())
    }

    override suspend fun insertCanteens(canteens: List<Canteen>): List<Long> {
        return try {
            canteenDao.insertCanteens(canteens.toDbEntity())
        } catch (e: Exception) {
            listOf()
        }
    }


    override fun getCanteens(): Flow<AppResult<List<Canteen>>> {
        return canteenDao.getAllCanteens().map {
            when {
                it.isEmpty() -> AppResult.Error(ErrorReason.Db.EmptyResult)
                else -> AppResult.Success(it.toEntity())
            }
        }
    }
}