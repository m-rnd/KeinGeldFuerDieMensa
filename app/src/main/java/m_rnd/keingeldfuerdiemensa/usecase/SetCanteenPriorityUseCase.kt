package m_rnd.keingeldfuerdiemensa.usecase

import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import javax.inject.Inject

class SetCanteenPriorityUseCase @Inject constructor(
    private val canteenRepository: CanteenRepository
) : SuspendUseCase<SetCanteenPriorityUseCase.Input, Unit>() {

    class Input(val canteen: Canteen, val priority: Int)

    override suspend fun call(input: Input): AppResult<Unit> {
        return canteenRepository.setCanteenPriority(input.canteen, input.priority)
    }
}