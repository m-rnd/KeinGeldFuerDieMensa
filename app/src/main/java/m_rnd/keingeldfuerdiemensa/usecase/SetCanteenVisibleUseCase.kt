package m_rnd.keingeldfuerdiemensa.usecase

import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import javax.inject.Inject

class SetCanteenVisibleUseCase @Inject constructor(
    private val canteenRepository: CanteenRepository
) : SuspendUseCase<SetCanteenVisibleUseCase.Input, Unit>() {

    class Input(val canteen: Canteen, val isVisible: Boolean)

    override suspend fun call(input: Input): AppResult<Unit> {
        return canteenRepository.setCanteenVisible(input.canteen, input.isVisible)
    }
}