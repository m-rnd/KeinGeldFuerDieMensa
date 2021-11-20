package m_rnd.keingeldfuerdiemensa.repository

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.datasource.api.OpenMensaDataSource
import m_rnd.keingeldfuerdiemensa.datasource.db.DbCanteenDataSource
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.entities.util.extensions.mapSuccess
import javax.inject.Inject

class CanteenRepositoryImpl @Inject constructor(
    private val openMensaDataSource: OpenMensaDataSource,
    private val dbCanteenDataSource: DbCanteenDataSource,
) : CanteenRepository {

    override fun getCanteensWithMealsForDay(date: String): Flow<FlowState<List<Canteen>>> {
        return dbCanteenDataSource.getCanteens()
            .mapSuccess {
                it.mapNotNull { mensa ->
                    when (val meals = openMensaDataSource.getMealsForCanteen(mensa.id, date)) {
                        is AppResult.Success -> mensa.copy(meals = meals.data)
                        else -> null
                    }
                }
            }
    }

    override fun getCanteens(): Flow<FlowState<List<Canteen>>> {
        return dbCanteenDataSource.getCanteens()
    }

    override suspend fun getCanteenSearchResults(): AppResult<List<CanteenSearchResult>> {
        return openMensaDataSource.getCanteens()
    }

    override suspend fun saveCanteen(canteen: Canteen): AppResult<Unit> {
        return affectedRowsToResult(dbCanteenDataSource.insertCanteen(canteen))
    }

    fun affectedRowsToResult(rows: Long): AppResult<Unit> = rows.takeIf { it > 0 }?.let { AppResult.Success(Unit) } ?: AppResult.Error(ErrorReason.Db.EmptyResult)
}