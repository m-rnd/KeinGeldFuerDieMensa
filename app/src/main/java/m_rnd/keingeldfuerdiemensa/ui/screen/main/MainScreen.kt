package m_rnd.keingeldfuerdiemensa.ui.screen.main

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import m_rnd.keingeldfuerdiemensa.NavigationDestination
import m_rnd.keingeldfuerdiemensa.presentation.MainViewModel
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.daybar.DayItem
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.daybar.SettingsItem
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.meallist.PageLayout

@ExperimentalPagerApi
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavController) {

    val days = remember {
        viewModel.getMensasForNextDays()
    }
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = days.size)
    Scaffold(bottomBar = {
        Card(shape = RectangleShape, elevation = 12.dp) {
            ScrollableTabRow(
                modifier = Modifier.height(72.dp),
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
                                RoundedCornerShape(4.dp)),
                        height = 72.dp
                    )
                }
            ) {
                days.forEachIndexed { index, mensa ->
                    Tab(
                        modifier = Modifier.fillMaxHeight(),
                        text = {
                            DayItem(mensa.day)
                        },
                        selected = pagerState.currentPage == index,
                        selectedContentColor = MaterialTheme.colors.onSurface,
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
                    selected = pagerState.currentPage == days.size,
                    selectedContentColor = MaterialTheme.colors.onSurface,
                    unselectedContentColor = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                    onClick = {
                        navController.navigate(NavigationDestination.SETTINGS.name)
                    },
                )
            }
        }
    }) {
        HorizontalPager(modifier = Modifier.padding(it), state = pagerState) { page ->
            PageLayout(mensaflow = days[page].mensas)
        }
    }
}