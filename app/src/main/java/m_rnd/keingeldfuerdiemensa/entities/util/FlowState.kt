package m_rnd.keingeldfuerdiemensa.entities.util

import java.io.Serializable


sealed class FlowState<out T> : Serializable {

    data class Success<out T>(val data: T) : FlowState<T>()

    object Loading: FlowState<Nothing>()

    data class Error(val reason: ErrorReason) : FlowState<Nothing>() {
        constructor() : this(ErrorReason.Unknown)
    }
}