package m_rnd.keingeldfuerdiemensa.entities.util

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