package m_rnd.keingeldfuerdiemensa.datasource.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import m_rnd.keingeldfuerdiemensa.datasource.api.implementation.MensaService
import m_rnd.keingeldfuerdiemensa.datasource.api.mapper.toEntity
import m_rnd.keingeldfuerdiemensa.datasource.api.util.callApi
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import javax.inject.Inject


class ApiMealDataSourceImpl @Inject constructor(
    private val mensaService: MensaService
): ApiMealDataSource {
    override fun getMeals(canteenId: Int, date: String): Flow<AppResult<List<Meal>>> {
        return flow {
            emit(AppResult.Loading)
            emit(
                callApi(
                    call = {
                        mensaService.getMealsForCanteenOfDay(canteenId, date)
                    },
                    mapper = { it.toEntity() }
                )
            )
        }
    }

    override suspend fun getMealsAsync(canteenId: Int, date: String): AppResult<List<Meal>> {
        return callApi(
            call = {
                mensaService.getMealsForCanteenOfDay(canteenId, date)
            },
            mapper = { it.toEntity() }
        )
    }
}


