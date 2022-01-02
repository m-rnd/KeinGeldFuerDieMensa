package m_rnd.keingeldfuerdiemensa.entities.util

import java.io.Serializable


sealed class AppResult<out T> : Serializable {

    data class Success<out T>(val data: T) : AppResult<T>()

    data class Error(val reason: ErrorReason) : AppResult<Nothing>() {
        constructor() : this(ErrorReason.Unknown)
    }

    fun <R> mapSuccess(transform: (T) -> R): AppResult<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Error -> this
        }
    }


    suspend fun <R> onSuccess(call: suspend (T) -> AppResult<R>): AppResult<R>{
        return when(this) {
            is Success -> call(data)
            is Error -> this
        }
    }
}

fun <T> AppResult<List<T>>.filterSuccess(predicate: (T) -> Boolean): AppResult<List<T>> {
    return mapSuccess { it.filter(predicate) }
}

fun <T> AppResult<T>.getDataOr(other: T?): T? {
    return when (this){
        is AppResult.Success -> data
        is AppResult.Error -> other
    }
}