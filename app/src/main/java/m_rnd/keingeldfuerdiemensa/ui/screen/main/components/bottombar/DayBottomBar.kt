package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.bottombar

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import m_rnd.keingeldfuerdiemensa.entities.MealPlan
import m_rnd.keingeldfuerdiemensa.entities.mock.PreviewEntity
import m_rnd.keingeldfuerdiemensa.presentation.MainMenuItem
import m_rnd.keingeldfuerdiemensa.ui.components.util.translucentSurfaceColor
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme
import m_rnd.keingeldfuerdiemensa.ui.theme.BottomBarElevation
import m_rnd.keingeldfuerdiemensa.ui.theme.CustomCornerRadius
import m_rnd.keingeldfuerdiemensa.ui.theme.MainScreenBottomBarHeight

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DayBottomBar(
    meals: List<MealPlan>,
    pagerState: PagerState,
    onMenuItemClick: (MainMenuItem) -> Unit
) {
    var isMenuVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Surface(
        color = translucentSurfaceColor(BottomBarElevation),
        elevation = BottomBarElevation
    ) {
        ScrollableTabRow(
            modifier = Modifier
                .navigationBarsPadding()
                .height(MainScreenBottomBarHeight),
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.primary.copy(alpha = 0.1f),
            edgePadding = 8.dp,
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(CustomCornerRadius)),
                    height = MainScreenBottomBarHeight
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
        modifier = Modifier.fillMaxHeight(),
        text = { tabItem(isSelected) },
        selected = isSelected,
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
        onClick = onClick,
    )
}

@ExperimentalPagerApi
@Preview
@Composable
fun DayBottomBarPreview() {
    AppTheme {
        DayBottomBar(
            meals = listOf(PreviewEntity.MealPlanMock()),
            pagerState = rememberPagerState(pageCount = 2),
            onMenuItemClick = { },
        )
    }
}