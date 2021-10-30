package m_rnd.keingeldfuerdiemensa.entities.util

import java.io.Serializable


sealed class AppResult<out T> : Serializable {

    data class Success<out T>(val data: T) : AppResult<T>()

    object Loading: AppResult<Nothing>()

    data class Error(val reason: ErrorReason) : AppResult<Nothing>() {
        constructor() : this(ErrorReason.Unknown)
    }

    fun <R> mapSuccess(transform: (T) -> R): AppResult<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Loading -> this
            is Error -> this
        }
    }
}