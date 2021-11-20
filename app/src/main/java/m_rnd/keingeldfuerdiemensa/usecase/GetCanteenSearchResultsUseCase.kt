package m_rnd.keingeldfuerdiemensa.usecase

import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import javax.inject.Inject

class GetCanteenSearchResultsUseCase @Inject constructor(
    private val canteenRepository: CanteenRepository
) : SuspendUseCase<Unit, List<CanteenSearchResult>>() {

    override suspend fun call(input: Unit): AppResult<List<CanteenSearchResult>> {
        return canteenRepository.getCanteenSearchResults()
    }
}