package m_rnd.keingeldfuerdiemensa.entities.util

import java.io.Serializable

sealed class ErrorReason: Serializable {

    object Unknown: ErrorReason()

    data class UseCaseException(val exception: Throwable): ErrorReason()

    data class Api(
         val additionalInfo: String
    ): ErrorReason()

    sealed class Db: ErrorReason(){
        object EmptyResult: Db()
    }
}