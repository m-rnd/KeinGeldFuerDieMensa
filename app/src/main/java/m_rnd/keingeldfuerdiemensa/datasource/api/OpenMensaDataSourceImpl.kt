package m_rnd.keingeldfuerdiemensa.datasource.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import m_rnd.keingeldfuerdiemensa.datasource.api.implementation.OpenMensaService
import m_rnd.keingeldfuerdiemensa.datasource.api.mapper.toEntity
import m_rnd.keingeldfuerdiemensa.datasource.api.util.callApi
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import javax.inject.Inject


class OpenMensaDataSourceImpl @Inject constructor(
    private val openMensaService: OpenMensaService
) : OpenMensaDataSource {
    override fun getMeals(canteenId: Int, date: String): Flow<AppResult<List<Meal>>> {
        return flow {
            emit(AppResult.Loading)
            emit(
                callApi(
                    call = {
                        openMensaService.getMealsForCanteenOfDay(canteenId, date)
                    },
                    mapper = { it.toEntity() }
                )
            )
        }
    }

    override suspend fun getCanteensAsync(): AppResult<List<CanteenSearchResult>> = callApi(
        call = {
            openMensaService.getCanteens()
        },
        mapper = { it.toEntity() }
    )

    override suspend fun getMealsAsync(canteenId: Int, date: String): AppResult<List<Meal>> {
        return callApi(
            call = {
                openMensaService.getMealsForCanteenOfDay(canteenId, date)
            },
            mapper = { it.toEntity() }
        )
    }
}


