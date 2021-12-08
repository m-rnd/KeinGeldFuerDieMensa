package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.mock.PreviewEntity
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.ui.components.banner.ErrorBanner
import m_rnd.keingeldfuerdiemensa.ui.components.util.LoadingIndicator


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealPlan(
    contentPadding: PaddingValues = PaddingValues(0.dp),
    canteenState: FlowState<List<Canteen>>,
    onAddCanteenClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        when (canteenState) {
            is FlowState.Error -> {
                if (canteenState.reason == ErrorReason.Db.EmptyResult) {
                    EmptyMealPlan(onAddCanteenClick)
                } else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorBanner(errorReason = canteenState.reason)
                    }
                }
            }
            is FlowState.Loading -> {
                LoadingIndicator()
            }
            is FlowState.Success -> {
                LazyColumn(
                    contentPadding = contentPadding,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    canteenState.data.forEach { canteen ->
                        stickyHeader { MealPlanCanteenTitle(canteenName = canteen.name) }

                        val mealsByCategory = canteen.meals.groupBy { it.category }

                        mealsByCategory.forEach { (category, meals) ->
                            item { MealPlanCategoryTitle(categoryName = category) }
                            itemsIndexed(meals) { index, meal ->
                                MealPlanItem(
                                    mealTitle = meal.name,
                                    mealDescription = meal.notes.joinToString(" • "),
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

@Preview
@Composable
fun MealPlanPreview() {
    MealPlan(
        canteenState = FlowState.Success(listOf(PreviewEntity.CanteenMock())),
        onAddCanteenClick = {}
    )
}