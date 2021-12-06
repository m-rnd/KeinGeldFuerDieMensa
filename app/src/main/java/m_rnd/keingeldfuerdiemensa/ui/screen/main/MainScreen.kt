package m_rnd.keingeldfuerdiemensa.ui.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import m_rnd.keingeldfuerdiemensa.presentation.MainViewModel
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.StatusBarType
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.SystemUiScaffold
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.daybar.DayBottomBar
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.meallist.PageLayout

@ExperimentalPagerApi
@Composable
fun MainScreen(viewModel: MainViewModel) {

    val days = remember {
        viewModel.getCanteensForNextDays()
    }

    val pagerState = rememberPagerState(pageCount = days.size)

    SystemUiScaffold(
        statusBarType = StatusBarType.BACKGROUND_TRANSLUCENT,
        bottomBar = {
            DayBottomBar(
                meals = days,
                pagerState = pagerState,
                onSettingsIconClick = viewModel::navigateToSettingsScreen
            )
        }
    ) { contentPadding ->
        HorizontalPager(state = pagerState) { page ->
            PageLayout(
                contentPadding = contentPadding,
                canteenFlow = days[page].canteens,
                onAddMensaClick = {
                    viewModel.navigateToAddCanteenScreen()
                }
            )
        }
    }
}