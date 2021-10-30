package m_rnd.keingeldfuerdiemensa.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import m_rnd.keingeldfuerdiemensa.datasource.api.MensaService
import m_rnd.keingeldfuerdiemensa.datasource.api.mapper.toEntity
import m_rnd.keingeldfuerdiemensa.datasource.api.util.callApi
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import javax.inject.Inject


class MealDataSourceImpl @Inject constructor(
    private val mensaService: MensaService
): MealDataSource {
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
}


