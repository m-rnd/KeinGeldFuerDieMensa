package m_rnd.keingeldfuerdiemensa.entities.util

import java.io.Serializable

sealed class ErrorReason: Serializable {

    object Unknown: ErrorReason()

    data class UseCaseException(val exception: Throwable): ErrorReason()

    sealed class Api: ErrorReason() {
        object NoConnection: Api()
        object ErrorResponse: Api()
        object Other: Api()
    }

    sealed class Db: ErrorReason(){
        object EmptyResult: Db()
    }
}