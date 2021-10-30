package m_rnd.keingeldfuerdiemensa.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import m_rnd.keingeldfuerdiemensa.common.extensions.toAPIDate
import m_rnd.keingeldfuerdiemensa.entities.Mensa
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.repository.MealRepository
import javax.inject.Inject


class GetMensaUseCase @Inject constructor(
    private val mealRepository: MealRepository,
) {

    operator fun invoke(timestamp: Long): Flow<AppResult<List<Mensa>>> {
        val date = timestamp.toAPIDate()
        val mensaPark = mealRepository.getMeals(63, date)
        val mensaBoGa = mealRepository.getMeals(72, date)
        val mensaMedi = mealRepository.getMeals(67, date)
        val mensaHTWK = mealRepository.getMeals(64, date)
        val mensaPeter = mealRepository.getMeals(68, date)



        return combine(mensaPark,
            mensaBoGa,
            mensaMedi,
            mensaHTWK,
            mensaPeter) { parkResult, bogaResult, mediResult, htwkResult, peterResult ->
            when {
//                parkResult is AppResult.Error -> parkResult
//                bogaResult is AppResult.Error -> bogaResult
//                mediResult is AppResult.Error -> mediResult
                else -> {
                    AppResult.Success(
                        mutableListOf<Mensa>().apply {
                            if (htwkResult is AppResult.Success) {
                                add(Mensa(
                                    "Mensa HTWK",
                                    htwkResult.data
                                ))
                            }
                            if (peterResult is AppResult.Success) {
                                add(Mensa(
                                    "Mensa Peterssteinweg",
                                    peterResult.data
                                ))
                            }
                            if (bogaResult is AppResult.Success) {
                                add(Mensa(
                                    "Mensaria am Botanischen Garten",
                                    bogaResult.data
                                ))
                            }
                            if (mediResult is AppResult.Success) {
                                add(Mensa(
                                    "Mensa am Medizincampus",
                                    mediResult.data
                                ))
                            }
                            if (parkResult is AppResult.Success) {
                                add(Mensa(
                                    "Mensa am Park",
                                    parkResult.data
                                ))
                            }
                        }
                    )
                }
            }
        }.distinctUntilChanged()
    }
}