package m_rnd.keingeldfuerdiemensa.entities

import java.io.Serializable

data class PriceInfo(
    val students: Float?,
    val employees: Float?,
    val pupils: Float?,
    val others: Float?,
): Serializable
