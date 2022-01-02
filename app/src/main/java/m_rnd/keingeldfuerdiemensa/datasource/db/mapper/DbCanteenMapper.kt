package m_rnd.keingeldfuerdiemensa.datasource.db.mapper

import m_rnd.keingeldfuerdiemensa.datasource.db.model.DbCanteen
import m_rnd.keingeldfuerdiemensa.entities.Canteen

fun List<DbCanteen>.toEntity(): List<Canteen> = mapNotNull { it.toEntity() }
fun List<Canteen>.toDbEntity(): List<DbCanteen> = mapNotNull { it.toDbEntity() }

fun DbCanteen.toEntity(): Canteen {
    return Canteen(id = id, name = name, meals = listOf(), isVisible = isVisible, priority = priority)
}

fun Canteen.toDbEntity(): DbCanteen {
    return DbCanteen(id = id, name = name, priority = priority, isVisible = isVisible)
}