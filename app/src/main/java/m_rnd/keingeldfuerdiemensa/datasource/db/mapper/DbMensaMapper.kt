package m_rnd.keingeldfuerdiemensa.datasource.db.mapper

import m_rnd.keingeldfuerdiemensa.datasource.db.model.DbMensa
import m_rnd.keingeldfuerdiemensa.entities.Mensa

fun List<DbMensa>.toEntity(): List<Mensa> = mapNotNull { it.toEntity() }
fun List<Mensa>.toDbEntity(): List<DbMensa> = mapNotNull { it.toDbEntity() }

fun DbMensa.toEntity(): Mensa {
    return Mensa(id = id, name = name, meals = listOf())
}

fun Mensa.toDbEntity(): DbMensa {
    return DbMensa(id = id, name = name)
}