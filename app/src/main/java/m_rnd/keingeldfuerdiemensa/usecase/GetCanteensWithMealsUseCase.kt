package m_rnd.keingeldfuerdiemensa.usecase

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.common.extensions.toAPIDate
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import javax.inject.Inject


class GetCanteensWithMealsUseCase @Inject constructor(
    private val canteenRepository: CanteenRepository,
): FlowUseCase<Long, List<Canteen>>() {

    override fun call(input: Long): Flow<FlowState<List<Canteen>>> {
        val date = input.toAPIDate()
        return canteenRepository.getCanteensWithMealsForDay(date)
    }
}