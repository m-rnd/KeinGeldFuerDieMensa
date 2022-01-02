package m_rnd.keingeldfuerdiemensa.datasource.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import m_rnd.keingeldfuerdiemensa.datasource.db.implementation.CanteenDao
import m_rnd.keingeldfuerdiemensa.datasource.db.mapper.toDbEntity
import m_rnd.keingeldfuerdiemensa.datasource.db.mapper.toEntity
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import javax.inject.Inject


class DbCanteenDataSourceImpl @Inject constructor(
    private val canteenDao: CanteenDao,
) : DbCanteenDataSource {

    override suspend fun insertCanteen(canteen: Canteen): AppResult<Unit> {
        return affectedRowsToResult(canteenDao.insertCanteen(canteen.toDbEntity()))
    }

    override suspend fun deleteCanteen(canteen: Canteen): AppResult<Unit> {
        return primaryKeyToResult(canteenDao.deleteCanteen(canteen.toDbEntity()))
    }

    override suspend fun setCanteenPriority(canteen: Canteen, priority: Int): AppResult<Unit> {
        return primaryKeyToResult(canteenDao.setCanteenPriority(canteen.id, priority))
    }

    override suspend fun setCanteenVisible(canteen: Canteen, isVisible: Boolean): AppResult<Unit> {
        return primaryKeyToResult(canteenDao.setCanteenVisible(canteen.id, isVisible))
    }

    override fun getCanteens(): Flow<FlowState<List<Canteen>>> {
        return addStateToFlow(canteenDao.getAllCanteens()) { it.toEntity() }
    }

    override fun getVisibleCanteens(): Flow<FlowState<List<Canteen>>> {
        return addStateToFlow(canteenDao.getAllVisibleCanteens()) { it.toEntity() }
    }


    private fun <T, R> addStateToFlow(flow: Flow<List<T>>, mapper: (List<T>) -> List<R>): Flow<FlowState<List<R>>> {
        return flow.map {
            when {
                it.isEmpty() -> FlowState.Error(ErrorReason.Db.EmptyResult)
                else -> FlowState.Success(mapper(it))
            }
        }
    }

    private fun affectedRowsToResult(rows: Long): AppResult<Unit> =
        rows.takeIf { it > 0 }?.let { AppResult.Success(Unit) }
            ?: AppResult.Error(ErrorReason.Db.EmptyResult)

    private fun primaryKeyToResult(rows: Int): AppResult<Unit> =
        rows.takeIf { it > 0 }?.let { AppResult.Success(Unit) }
            ?: AppResult.Error(ErrorReason.Db.EmptyResult)
}