package m_rnd.keingeldfuerdiemensa.usecase

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.common.extensions.toAPIDate
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import javax.inject.Inject


class GetCanteensWithMealsUseCase @Inject constructor(
    private val canteenRepository: CanteenRepository,
): UseCase<Long, List<Canteen>>() {

    override val call: (Long) -> Flow<AppResult<List<Canteen>>>
        get() = { timestamp ->
            val date = timestamp.toAPIDate()
            canteenRepository.getCanteensWithMealsForDay(date)
        }
}