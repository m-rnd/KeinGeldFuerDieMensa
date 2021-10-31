package m_rnd.keingeldfuerdiemensa.entities

import java.io.Serializable

data class Mensa(
  val id: Int,
  val name: String,
  val meals: List<Meal>
): Serializable