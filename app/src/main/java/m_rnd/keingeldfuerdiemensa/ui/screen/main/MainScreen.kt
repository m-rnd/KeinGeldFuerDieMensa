package m_rnd.keingeldfuerdiemensa.ui.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import m_rnd.keingeldfuerdiemensa.entities.MealPlan
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.presentation.main.MainViewModel
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.SystemUiBottomSheetScaffold
import m_rnd.keingeldfuerdiemensa.ui.components.util.coloroverrides.translucentBackgroundColor
import m_rnd.keingeldfuerdiemensa.ui.components.util.pxToDp
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.MainScreenCollapsedSheetContent
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.bottombar.DayBottomBarTabPadding
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan.MealPlan
import m_rnd.keingeldfuerdiemensa.ui.theme.CustomCornerRadius
import m_rnd.keingeldfuerdiemensa.ui.theme.MainScreenBottomBarHeight

val SheetDragHandlePillHeight = 4.dp
val SheetDragHandlePadding = 4.dp
val SheetDragHandleHeight = SheetDragHandlePillHeight + 2 * SheetDragHandlePadding


@Composable
fun MainScreen(viewModel: MainViewModel) {
    Content(
        mealPlans = remember { viewModel.getMealPlans() },
        onAddCanteenClick = viewModel::navigateToAddCanteenScreen,
        onSheetExpanded = viewModel::navigateToSettings
    )

}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    mealPlans: List<MealPlan>,
    onAddCanteenClick: () -> Unit,
    onSheetExpanded: (MealPlan) -> Unit,
) {
    val systemBarBottomHeight = WindowInsets.systemBars.getBottom(LocalDensity.current)
    val pagerState = rememberPagerState { mealPlans.size }
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.PartiallyExpanded,
            confirmValueChange = { it != SheetValue.Hidden } // disable hidden state
        )
    )

    LaunchedEffect(scaffoldState.bottomSheetState) {
        scaffoldState.bottomSheetState.partialExpand()
        snapshotFlow { scaffoldState.bottomSheetState.currentValue }.collect { currentValue ->
            if (currentValue == SheetValue.Expanded) {
                onSheetExpanded(mealPlans[pagerState.currentPage])
                scaffoldState.bottomSheetState.partialExpand()
            }
        }
    }

    SystemUiBottomSheetScaffold(
        collapsedSheetHeight = MainScreenBottomBarHeight + systemBarBottomHeight.pxToDp() + SheetDragHandleHeight,
        scaffoldState = scaffoldState,
        collapsedSheetCornerRadius = CustomCornerRadius + SheetDragHandleHeight + DayBottomBarTabPadding,
        sheetContent = { animationProgressProvider ->
            MainScreenCollapsedSheetContent(animationProgressProvider, mealPlans, pagerState)
        }
    ) { contentPadding ->
        // colored toolbar spacer
        Spacer(
            Modifier
                .zIndex(1f)
                .background(translucentBackgroundColor())
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .fillMaxWidth()
        )
        HorizontalPager(
            state = pagerState,
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