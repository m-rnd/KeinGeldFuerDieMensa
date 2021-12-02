package m_rnd.keingeldfuerdiemensa.datasource.api

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

    override suspend fun getCanteens(): AppResult<List<CanteenSearchResult>> = callApi(
        call = {
            openMensaService.getCanteens()
        },
        mapper = { it.toEntity() }
    )

    override suspend fun getMealsForCanteen(canteenId: Int, date: String): AppResult<List<Meal>> {
        return callApi(
            call = {
                openMensaService.getMealsForCanteenOfDay(canteenId, date)
            },
            mapper = { it.toEntity() }
        )
    }
}


