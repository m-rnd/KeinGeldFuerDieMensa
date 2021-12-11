package m_rnd.keingeldfuerdiemensa.ui.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import m_rnd.keingeldfuerdiemensa.entities.MealPlan
import m_rnd.keingeldfuerdiemensa.entities.mock.PreviewEntity.MealPlanMock
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.presentation.MainViewModel
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.StatusBarType
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.SystemUiScaffold
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.bottombar.DayBottomBar
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan.MealPlan
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme

@ExperimentalPagerApi
@Composable
fun MainScreen(viewModel: MainViewModel) {
    Content(
        mealPlans = viewModel.getMealPlans(),
        onAddCanteenClick = viewModel::navigateToAddCanteenScreen,
        onSettingsClick = viewModel::navigateToSettingsScreen
    )

}

@ExperimentalPagerApi
@Composable
private fun Content(
    mealPlans: List<MealPlan>,
    onAddCanteenClick: () -> Unit,
    onSettingsClick: () -> Unit
) {

    val pagerState = rememberPagerState(pageCount = mealPlans.size)

    SystemUiScaffold(
        statusBarType = StatusBarType.BACKGROUND_TRANSLUCENT,
        bottomBar = {
            DayBottomBar(
                meals = mealPlans,
                pagerState = pagerState,
                onSettingsIconClick = onSettingsClick
            )
        }
    ) { contentPadding ->
        HorizontalPager(state = pagerState) { page ->
            MealPlan(
                contentPadding = contentPadding,
                canteenState = mealPlans[page].canteens.collectAsState(initial = FlowState.Loading).value,
                onAddCanteenClick = onAddCanteenClick
            )
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun MainScreenPreview() {
    AppTheme {
        Content(
            mealPlans = listOf(MealPlanMock()),
            onAddCanteenClick = { },
            onSettingsClick = { }
        )
    }
}