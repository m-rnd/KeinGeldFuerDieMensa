package m_rnd.keingeldfuerdiemensa.datasource.api.model

import java.io.Serializable

data class ApiMeal(
    val id: Int?,
    val name: String?,
    val notes: List<String>?,
    val prices: ApiPriceInfo?,
    val category: String?
): Serializable