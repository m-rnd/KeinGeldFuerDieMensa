package m_rnd.keingeldfuerdiemensa.datasource.api.model

import java.io.Serializable

data class ApiCanteenSearchResult(
    val id: Int? = null,
    val name: String? = null,
    val city: String? = null,
    val address: String? = null,
    val coordinates: List<Float>? = null
): Serializable