package m_rnd.keingeldfuerdiemensa.datasource.api.mapper

import m_rnd.keingeldfuerdiemensa.datasource.api.model.ApiCanteenSearchResult
import m_rnd.keingeldfuerdiemensa.datasource.common.util.generateMappingError
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult


fun List<ApiCanteenSearchResult>.toEntity(): List<CanteenSearchResult> =
    mapNotNull { it.toEntity() }

fun ApiCanteenSearchResult.toEntity(): CanteenSearchResult? {
    return when {
        id == null -> generateMappingError(::id)
        name == null -> generateMappingError(::name)
        city == null -> generateMappingError(::city)
        address == null -> generateMappingError(::address)
        coordinates == null -> generateMappingError(::coordinates)
        else -> CanteenSearchResult(
            id = id,
            name = name,
            city = city,
            address = address,
            coordinates = coordinates
        )
    }
}
