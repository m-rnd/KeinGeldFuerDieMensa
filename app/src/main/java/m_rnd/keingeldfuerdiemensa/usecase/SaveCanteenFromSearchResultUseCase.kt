package m_rnd.keingeldfuerdiemensa.usecase

import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import javax.inject.Inject

class SaveCanteenFromSearchResultUseCase @Inject constructor(
    private val canteenRepository: CanteenRepository
) : SuspendUseCase<CanteenSearchResult, Unit>() {

    override val call: suspend (CanteenSearchResult) -> AppResult<Unit>
        get() = {
            val canteen = Canteen(
                id = it.id,
                name = it.name,
                meals = emptyList()
            )
            canteenRepository.saveCanteen(canteen)
        }
}