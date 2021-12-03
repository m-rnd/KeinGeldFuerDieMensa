package m_rnd.keingeldfuerdiemensa.datasource.api.util

import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import retrofit2.Response
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException


suspend fun <ApiEntity, Entity> callApi(
    call: suspend () -> Response<ApiEntity>,
    mapper: (ApiEntity) -> Entity,
): AppResult<Entity> {
    return try {
        val response = call()
        when {
            response.isSuccessful -> AppResult.Success(response.body()!!).mapSuccess(mapper)
            else -> {
                Timber.e("API returned error: ${response.errorBody()?.string() ?: response.code()}")
                AppResult.Error(ErrorReason.Api.ErrorResponse)
            }
        }
    } catch (e: Exception) {
        val reason = when (e) {
            is UnknownHostException,
            is ConnectException -> ErrorReason.Api.NoConnection
            else -> ErrorReason.Api.Other
        }

        e.message?.let { message ->
            Timber.e("exception in API: ${e.message ?: "unknown"}")
        }

        AppResult.Error(reason)
    }
}