package m_rnd.keingeldfuerdiemensa.repository

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.Mensa
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult

interface MensaRepository {
    fun getMensasForDay(date: String): Flow<AppResult<List<Mensa>>>
}