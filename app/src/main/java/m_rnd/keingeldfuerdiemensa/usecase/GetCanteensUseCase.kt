package m_rnd.keingeldfuerdiemensa.usecase

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import javax.inject.Inject

class GetCanteensUseCase @Inject constructor(
    private val canteenRepository: CanteenRepository
) : FlowUseCase<Unit, List<Canteen>>() {

    override fun call(input: Unit): Flow<FlowState<List<Canteen>>> {
        return canteenRepository.getCanteens()
    }
}