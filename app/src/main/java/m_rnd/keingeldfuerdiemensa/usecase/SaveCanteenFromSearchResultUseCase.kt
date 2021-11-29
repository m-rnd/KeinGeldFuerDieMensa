package m_rnd.keingeldfuerdiemensa.usecase

import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import javax.inject.Inject

class SaveCanteenFromSearchResultUseCase @Inject constructor(
    private val canteenRepository: CanteenRepository
) : SuspendUseCase<Canteen, Unit>() {

    override suspend fun call(input: Canteen): AppResult<Unit> {
        return canteenRepository.saveCanteen(input)
    }
}