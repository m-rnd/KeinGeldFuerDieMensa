package m_rnd.keingeldfuerdiemensa.common.extensions

import android.text.format.DateUtils
import m_rnd.keingeldfuerdiemensa.common.DAY_IN_MS
import java.text.SimpleDateFormat
import java.util.*


private val apiFormatter: SimpleDateFormat by lazy { SimpleDateFormat("yyyy-MM-dd") }
private val visualDayFormatter: SimpleDateFormat by lazy { SimpleDateFormat("EEEE") }
private val visualDateFormatter: SimpleDateFormat by lazy { SimpleDateFormat("dd. MMM") }

fun Long.toAPIDate(): String {
    return apiFormatter.format(Date(this))
}

fun Long.toVisualDay(today: String, tomorrow: String): String {
    return when {
        DateUtils.isToday(this) -> today
        DateUtils.isToday(this - DAY_IN_MS) -> tomorrow
        else -> visualDayFormatter.format(Date(this))
    }
}

fun Long.toVisualDate(): String {
    return visualDateFormatter.format(Date(this))
}