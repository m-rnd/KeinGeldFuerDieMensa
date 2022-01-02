package m_rnd.keingeldfuerdiemensa.datasource.api.model

import java.io.Serializable

data class ApiPriceInfo(
    val students: Float?,
    val employees: Float?,
    val pupils: Float?,
    val others: Float?,
): Serializable