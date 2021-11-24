package m_rnd.keingeldfuerdiemensa.entities

import java.io.Serializable

data class Canteen(
  val id: Int,
  val name: String,
  val meals: List<Meal>,
  var visible: Boolean = true
): Serializable