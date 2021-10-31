package m_rnd.keingeldfuerdiemensa.entities

import java.io.Serializable


data class Meal(
    val id: Int,
    val name: String,
    val notes: List<String>,
    val prices: PriceInfo,
    val category: String
): Serializable
