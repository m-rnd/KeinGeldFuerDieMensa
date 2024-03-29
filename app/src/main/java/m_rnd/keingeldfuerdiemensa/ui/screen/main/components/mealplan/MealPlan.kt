package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.mock.PreviewEntity
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.ui.components.banner.ErrorBanner
import m_rnd.keingeldfuerdiemensa.ui.components.util.LoadingIndicator
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan.item.MealPlanCanteenTitle
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan.item.MealPlanItem
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme


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

                        if (mealsByCategory.isEmpty()) {
                            item {
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = stringResource(R.string.main_info_no_meals_found_for_day)
                                )
                            }
                        } else {
                            mealsByCategory.forEach { (category, meals) ->
                                item { MealPlanCategoryTitle(categoryName = category) }
                                itemsIndexed(meals) { index, meal ->
                                    MealPlanItem(
                                        mealTitles = meal.names,
                                        mealDescription = meal.notes.takeIf { it.isNotEmpty() }
                                            ?.joinToString(" • "),
                                        mealPrice = meal.prices.students ?: 0f
                                    )
                                    if (index < meals.size - 1) {
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
    }
}

@Preview
@Composable
fun MealPlanPreview() {
    AppTheme {
        MealPlan(
            canteenState = FlowState.Success(listOf(PreviewEntity.CanteenMock())),
            onAddCanteenClick = {}
        )
    }
}