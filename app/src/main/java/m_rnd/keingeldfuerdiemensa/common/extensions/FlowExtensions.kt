package m_rnd.keingeldfuerdiemensa.entities.util.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState

fun <T : List<R>, R> Flow<FlowState<T>>.filterSuccess(function: (R) -> Boolean): Flow<FlowState<List<R>>> {
    return map { appResult ->
        if (appResult is FlowState.Success) {
            FlowState.Success(appResult.data.filter(function))
        } else appResult
    }
}

fun <T, R> Flow<FlowState<T>>.mapSuccess(function: suspend (T) -> R): Flow<FlowState<R>> {
    return map { appResult ->
        when (appResult) {
            is FlowState.Success -> FlowState.Success(function(appResult.data))
            is FlowState.Error -> appResult
            is FlowState.Loading -> appResult
        }
    }
}