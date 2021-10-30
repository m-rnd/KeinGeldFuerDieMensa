package m_rnd.keingeldfuerdiemensa.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import m_rnd.keingeldfuerdiemensa.entities.Meal
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class MealRepositoryImplTest {


    private lateinit var mealList: List<Meal>
    private lateinit var mealRepository: MealRepositoryImpl

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() = runBlocking {
        mealList = listOf(
            mock(Meal::class.java),
            mock(Meal::class.java),
            mock(Meal::class.java)
        )

        mealRepository = MealRepositoryImpl()
        whenever(mealRepository.getMeals(any(), any())).thenReturn(flowOf(AppResult.Success(
            mealList
        )))
    }

    @Test
    fun dsafg() = runBlocking {
        mealRepository.getMeals(any(), any()).collect { result ->
            when (result) {
                is AppResult.Success -> Assert.assertEquals(mealList, result.data)
                else -> Assert.fail()
            }
        }
        Assert.assertTrue(true)
    }
}