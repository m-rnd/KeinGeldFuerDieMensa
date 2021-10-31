package m_rnd.keingeldfuerdiemensa.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import m_rnd.keingeldfuerdiemensa.datasource.api.ApiMealDataSource
import m_rnd.keingeldfuerdiemensa.datasource.db.DbMensaDataSource
import m_rnd.keingeldfuerdiemensa.entities.Mensa
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import javax.inject.Inject

class MensaRepositoryImpl @Inject constructor(
    private val apiMealDataSource: ApiMealDataSource,
    private val dbMensaDataSource: DbMensaDataSource,
) : MensaRepository {

    override fun getMensasForDay(date: String): Flow<AppResult<List<Mensa>>> {
        return dbMensaDataSource.getMensas()
            .map {
                when (it) {
                    is AppResult.Success -> {
                        AppResult.Success(it.data.mapNotNull { mensa ->
                            when (val meals = apiMealDataSource.getMealsAsync(mensa.id, date)) {
                                is AppResult.Success -> mensa.copy(meals = meals.data)
                                else -> null
                            }
                        })
                    }
                    is AppResult.Error -> {
                        // add dummy mensas
                        if (it.reason is ErrorReason.Db.EmptyResult) {
                            CoroutineScope(Dispatchers.IO).launch {
                                dbMensaDataSource.insertMensas(getDummyMensas())
                            }
                        }
                        it
                    }
                    else -> it
                }
            }
    }


    private fun getDummyMensas() = listOf(
        Mensa(
            id = 63,
            name = "Mensa am Park",
            meals = listOf()
        ),
        Mensa(
            id = 72,
            name = "Mensa BoGa",
            meals = listOf()
        ),
        Mensa(
            id = 67,
            name = "Mensa am Medizincampus",
            meals = listOf()
        ),
        Mensa(
            id = 64,
            name = "Mensa HTWK",
            meals = listOf()
        ),
        Mensa(
            id = 68,
            name = "Mensa am Peterssteinweg",
            meals = listOf()
        )
    )
}