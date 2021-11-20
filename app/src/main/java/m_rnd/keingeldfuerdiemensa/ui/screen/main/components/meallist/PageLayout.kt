package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.meallist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState


@Composable
fun PageLayout(
    canteenFlow: Flow<FlowState<List<Canteen>>>,
    onAddMensaClick: () -> Unit
) {
    val canteens = canteenFlow.collectAsState(initial = FlowState.Loading)

    Column(modifier = Modifier.fillMaxSize()) {
        when (val v = canteens.value) {
            is FlowState.Error -> {
                if (v.reason == ErrorReason.Db.EmptyResult) {
                    PageLayoutEmptyCanteenList(onAddMensaClick)
                }
            }
            is FlowState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            }
            is FlowState.Success -> {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    v.data.forEach { canteen ->
                        item { MealListTitle(canteenName = canteen.name) }

                        val mealsByCategory  = canteen.meals.groupBy { it.category }

                        mealsByCategory.forEach { (category, meals) ->
                            item { MealCategoryTitle(categoryName = category) }
                            itemsIndexed(meals) { index, meal ->
                                MealListItem(
                                    mealTitle = meal.name,
                                    mealDescription = meal.notes.joinToString(","),
                                    mealPrice = meal.prices.students ?: 0f
                                )
                                if (index < meals.size - 1)
                                    Divider(
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .width(144.dp)
                                    )
                            }
                        }

                    }
                }
            }
        }
    }
}