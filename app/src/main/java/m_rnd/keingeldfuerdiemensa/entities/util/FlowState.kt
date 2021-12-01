package m_rnd.keingeldfuerdiemensa.entities.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.Serializable


sealed class FlowState<out T> : Serializable {

    data class Success<out T>(val data: T) : FlowState<T>()

    object Loading: FlowState<Nothing>()

    data class Error(val reason: ErrorReason) : FlowState<Nothing>() {
        constructor() : this(ErrorReason.Unknown)
    }

    suspend fun <R> mapSuccess( transform: suspend (T) -> R): FlowState<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Error -> this
            is Loading -> this
        }
    }
}

suspend fun <T, R> Flow<FlowState<T>>.onFirstSuccess(call: suspend (T) -> AppResult<R>): AppResult<R> {
    val firstResult = this.filter { it !is FlowState.Loading }.first()
    return when(firstResult) {
        is FlowState.Success -> call(firstResult.data)
        is FlowState.Loading -> error("invalid state")
        is FlowState.Error -> AppResult.Error()
    }
}

suspend fun <T> Flow<FlowState<T>>.getFirstSuccessOr(default: T): T {
    val firstResult = this.filter { it !is FlowState.Loading }.first()
    return when(firstResult) {
        is FlowState.Success -> firstResult.data
        is FlowState.Error -> default
        FlowState.Loading -> error("invalid state")
    }
}

fun <T : List<R>, R> Flow<FlowState<T>>.filterSuccess(function: (R) -> Boolean): Flow<FlowState<List<R>>> {
    return mapSuccess { it.filter(function) }
}

fun <T, R> Flow<FlowState<T>>.mapSuccess(transform: suspend (T) -> R): Flow<FlowState<R>> {
    return map { flowState ->
        when (flowState) {
            is FlowState.Success -> FlowState.Success(transform(flowState.data))
            is FlowState.Error -> flowState
            is FlowState.Loading -> flowState
        }
    }
}