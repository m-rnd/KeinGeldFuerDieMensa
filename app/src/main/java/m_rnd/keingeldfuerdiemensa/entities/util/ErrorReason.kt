package m_rnd.keingeldfuerdiemensa.entities.util

import java.io.Serializable

sealed class ErrorReason(open val additionalInfo: String): Serializable {

    object Unknown: ErrorReason("unknown")

    data class Api(
        override val additionalInfo: String
    ): ErrorReason(additionalInfo)
}