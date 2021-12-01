package m_rnd.keingeldfuerdiemensa.usecase

import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.filterSuccess
import m_rnd.keingeldfuerdiemensa.entities.util.onFirstSuccess
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import javax.inject.Inject

class GetCanteenSearchResultsUseCase @Inject constructor(
    private val canteenRepository: CanteenRepository
) : SuspendUseCase<Unit, List<CanteenSearchResult>>() {

    override suspend fun call(input: Unit): AppResult<List<CanteenSearchResult>> {
        return canteenRepository.getCanteens().onFirstSuccess { usedCanteens ->
            val usedCanteenIds = usedCanteens.map { it.id }

            canteenRepository.getCanteenSearchResults().filterSuccess {
                usedCanteenIds.contains(it.id).not()
            }
        }
    }
}