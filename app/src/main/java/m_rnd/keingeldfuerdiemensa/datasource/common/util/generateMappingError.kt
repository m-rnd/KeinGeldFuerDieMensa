package m_rnd.keingeldfuerdiemensa.datasource.common.util

import timber.log.Timber
import kotlin.reflect.KProperty0

fun generateMappingError(property: KProperty0<Any?>): Nothing? {
    Timber.e("Mapping error in ${property.name}")
    return null
}