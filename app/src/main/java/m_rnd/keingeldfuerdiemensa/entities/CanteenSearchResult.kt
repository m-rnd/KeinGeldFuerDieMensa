package m_rnd.keingeldfuerdiemensa.entities

data class CanteenSearchResult(
    val id: Int,
    val name: String,
    val city: String,
    val address: String,
    val coordinates: List<Float>
)