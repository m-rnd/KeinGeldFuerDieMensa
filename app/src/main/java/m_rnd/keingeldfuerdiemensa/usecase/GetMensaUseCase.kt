package m_rnd.keingeldfuerdiemensa.usecase

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.common.extensions.toAPIDate
import m_rnd.keingeldfuerdiemensa.entities.Mensa
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.repository.MensaRepository
import javax.inject.Inject


class GetMensaUseCase @Inject constructor(
    private val mensaRepository: MensaRepository,
): UseCase<Long, List<Mensa>>() {

    override val call: (Long) -> Flow<AppResult<List<Mensa>>>
        get() = { timestamp ->
            val date = timestamp.toAPIDate()
            mensaRepository.getMensasForDay(date)
        }
}