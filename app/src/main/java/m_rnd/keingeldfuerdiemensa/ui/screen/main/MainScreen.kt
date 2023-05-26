package m_rnd.keingeldfuerdiemensa.ui.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import m_rnd.keingeldfuerdiemensa.entities.MealPlan
import m_rnd.keingeldfuerdiemensa.entities.mock.PreviewEntity.MealPlanMock
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.presentation.MainMenuItem
import m_rnd.keingeldfuerdiemensa.presentation.MainViewModel
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.StatusBarType
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.SystemUiScaffold
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.bottombar.DayBottomBar
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan.MealPlan
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Content(
        mealPlans = remember { viewModel.getMealPlans() },
        onAddCanteenClick = viewModel::navigateToAddCanteenScreen,
        onMenuItemClick = viewModel::navigateToMenuItemTarget
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    mealPlans: List<MealPlan>,
    onAddCanteenClick: () -> Unit,
    onMenuItemClick: (MainMenuItem) -> Unit
) {

    val pagerState = rememberPagerState()

    SystemUiScaffold(
        statusBarType = StatusBarType.BACKGROUND_TRANSLUCENT,
        bottomBar = {
            DayBottomBar(
                meals = mealPlans,
                pagerState = pagerState,
                onMenuItemClick = onMenuItemClick
            )
        }
    ) { contentPadding ->
        HorizontalPager(
            state = pagerState,
            pageCount = mealPlans.size,
            beyondBoundsPageCount = 1
        ) { page ->
            MealPlan(
                contentPadding = contentPadding,
                canteenState = mealPlans[page].canteens.collectAsState(initial = FlowState.Loading).value,
                onAddCanteenClick = onAddCanteenClick
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AppTheme {
        Content(
            mealPlans = listOf(MealPlanMock()),
            onAddCanteenClick = { },
            onMenuItemClick = { }
        )
    }
}