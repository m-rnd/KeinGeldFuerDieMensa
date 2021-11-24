package m_rnd.keingeldfuerdiemensa.entities

import java.io.Serializable

data class Canteen(
  val id: Int,
  val name: String,
  val meals: List<Meal> = emptyList(),
  val isVisible: Boolean = true,
  var priority: Int = 0
): Serializable