package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.bottombar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import m_rnd.keingeldfuerdiemensa.entities.MealPlan
import m_rnd.keingeldfuerdiemensa.entities.mock.PreviewEntity
import m_rnd.keingeldfuerdiemensa.presentation.MainMenuItem
import m_rnd.keingeldfuerdiemensa.ui.components.util.pagerTabIndicatorOffset
import m_rnd.keingeldfuerdiemensa.ui.theme.AppBarElevation
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme
import m_rnd.keingeldfuerdiemensa.ui.theme.CustomCornerRadius
import m_rnd.keingeldfuerdiemensa.ui.theme.MainScreenBottomBarHeight
import m_rnd.keingeldfuerdiemensa.ui.theme.TranslucentSurfaceAlpha

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DayBottomBar(
    meals: List<MealPlan>,
    pagerState: PagerState,
    onMenuItemClick: (MainMenuItem) -> Unit
) {
    var isMenuVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Surface(
        tonalElevation = AppBarElevation,
        modifier = Modifier.alpha(TranslucentSurfaceAlpha)
    ) {
        ScrollableTabRow(
            modifier = Modifier
                .navigationBarsPadding()
                .height(MainScreenBottomBarHeight),
            edgePadding = 8.dp,
            selectedTabIndex = pagerState.currentPage,
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(CustomCornerRadius)),
                    height = MainScreenBottomBarHeight,
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        ) {
            meals.forEachIndexed { index, canteen ->
                DayBottomBarTab(
                    isSelected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                ) { isSelected ->
                    DayItem(canteen.day, isSelected)
                }
            }
            DayBottomBarTab(
                isSelected = pagerState.currentPage == meals.size,
                onClick = { isMenuVisible = !isMenuVisible }
            ) {
                SettingsItem()
                SettingsMenu(
                    isVisible = isMenuVisible,
                    onMenuItemClick = onMenuItemClick,
                    onDismiss = { isMenuVisible = false }
                )
            }
        }
    }
}

@Composable
private fun DayBottomBarTab(
    isSelected: Boolean,
    onClick: () -> Unit,
    tabItem: @Composable (Boolean) -> Unit,
) {
    Tab(
        modifier = Modifier
            .fillMaxHeight()
            .zIndex(1f),
        text = { tabItem(isSelected) },
        selected = isSelected,
        onClick = onClick,
        selectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        unselectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun DayBottomBarPreview() {
    AppTheme {
        DayBottomBar(
            meals = listOf(PreviewEntity.MealPlanMock(), PreviewEntity.MealPlanMock()),
            pagerState = rememberPagerState(),
            onMenuItemClick = { },
        )
    }
}