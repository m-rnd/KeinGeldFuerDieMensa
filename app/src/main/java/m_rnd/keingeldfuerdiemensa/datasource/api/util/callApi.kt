package m_rnd.keingeldfuerdiemensa.datasource.api.util

import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import retrofit2.Response


suspend fun <ApiEntity, Entity> callApi(
    call: suspend () -> Response<ApiEntity>,
    mapper: (ApiEntity) -> Entity,
): AppResult<Entity> {
    return try {
        val response = call()
        when {
            response.isSuccessful -> AppResult.Success(response.body()!!).mapSuccess(mapper)
            else -> {
                response.errorBody()!!.let {
                    AppResult.Error(ErrorReason.Api(it.string()))
                }
            }
        }
    } catch (e: Exception) {
        AppResult.Error(e.message?.let { message ->
            ErrorReason.Api(message)
        } ?: ErrorReason.Unknown)
    }
}