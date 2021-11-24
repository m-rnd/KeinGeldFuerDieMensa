package m_rnd.keingeldfuerdiemensa.usecase

import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import javax.inject.Inject

class SaveCanteenFromSearchResultUseCase @Inject constructor(
    private val canteenRepository: CanteenRepository
) : SuspendUseCase<CanteenSearchResult, Unit>() {

    override suspend fun call(input: CanteenSearchResult): AppResult<Unit> {
        val canteen = Canteen(
            id = input.id,
            name = input.name
        )
        return canteenRepository.saveCanteen(canteen)
    }
}