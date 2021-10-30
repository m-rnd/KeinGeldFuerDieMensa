package m_rnd.keingeldfuerdiemensa.datasource.api

import m_rnd.keingeldfuerdiemensa.datasource.api.model.ApiMeal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MensaService {
    @GET("canteens/{canteenId}/days/{dayDate}/meals")
    suspend fun getMealsForCanteenOfDay(
        @Path("canteenId")
        canteenId: Int,
        @Path("dayDate")
        date: String
    ): Response<List<ApiMeal>>
}