package m_rnd.keingeldfuerdiemensa.entities.util.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult

fun <T : List<R>, R> Flow<AppResult<T>>.filterSuccess(function: (R) -> Boolean): Flow<AppResult<List<R>>> {
    return map { appResult ->
        if (appResult is AppResult.Success) {
            AppResult.Success(appResult.data.filter(function))
        } else appResult
    }
}