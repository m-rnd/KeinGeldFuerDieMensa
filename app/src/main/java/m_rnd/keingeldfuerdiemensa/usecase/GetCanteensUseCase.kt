package m_rnd.keingeldfuerdiemensa.usecase

import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.repository.CanteenRepository
import javax.inject.Inject

class GetCanteensUseCase @Inject constructor(
    private val canteenRepository: CanteenRepository
) : UseCase<Unit, List<Canteen>>() {

    override val call: (Unit) -> Flow<AppResult<List<Canteen>>>
        get() = {
        canteenRepository.getCanteens()
    }
}