package m_rnd.keingeldfuerdiemensa.entities

import java.io.Serializable


data class Meal(
    val id: Int,
    val names: List<String>,
    val notes: List<String>,
    val prices: PriceInfo,
    val category: String
): Serializable
