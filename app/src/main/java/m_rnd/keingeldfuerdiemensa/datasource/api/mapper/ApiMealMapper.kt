package m_rnd.keingeldfuerdiemensa.datasource.api.mapper

import m_rnd.keingeldfuerdiemensa.datasource.api.model.ApiMeal
import m_rnd.keingeldfuerdiemensa.datasource.api.model.ApiPriceInfo
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.PriceInfo
import timber.log.Timber
import kotlin.reflect.KProperty0

fun List<ApiMeal>.toEntity(): List<Meal> = mapNotNull { it.toEntity() }


fun ApiMeal.toEntity(): Meal? {
    val entityPrices = prices?.toEntity()
    return when {
        id == null -> generateMappingError(::id)
        name == null -> generateMappingError(::name)
        notes == null -> generateMappingError(::notes)
        entityPrices == null -> generateMappingError(::prices)
        category == null -> generateMappingError(::category)
        else -> Meal(
            id = id,
            name = name,
            notes = notes,
            prices = entityPrices,
            category = category
        )
    }
}

fun ApiPriceInfo.toEntity() = PriceInfo(
    students = students,
    employees = employees,
    pupils = pupils,
    others = others
)


fun generateMappingError(property: KProperty0<Any?>): Nothing? {
    Timber.e("Mapping error in ${property.name}")
    return null
}