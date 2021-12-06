package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.daybar

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import m_rnd.keingeldfuerdiemensa.entities.DayWithCanteens
import m_rnd.keingeldfuerdiemensa.ui.components.util.translucentSurfaceColor
import m_rnd.keingeldfuerdiemensa.ui.theme.BottomBarElevation
import m_rnd.keingeldfuerdiemensa.ui.theme.MainScreenBottomBarHeight

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DayBottomBar(
    meals: List<DayWithCanteens>,
    pagerState: PagerState,
    onSettingsIconClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    Card(
        backgroundColor = translucentSurfaceColor(BottomBarElevation),
        shape = RectangleShape,
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
                        .clip(
                            RoundedCornerShape(4.dp)
                        ),
                    height = MainScreenBottomBarHeight
                )
            }
        ) {
            meals.forEachIndexed { index, canteen ->
                Tab(
                    modifier = Modifier.fillMaxHeight(),
                    text = {
                        DayItem(canteen.day)
                    },
                    selected = pagerState.currentPage == index,
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
            Tab(
                modifier = Modifier.fillMaxHeight(),
                text = {
                    SettingsItem()
                },
                selected = pagerState.currentPage == meals.size,
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                onClick = onSettingsIconClick,
            )
        }
    }
}