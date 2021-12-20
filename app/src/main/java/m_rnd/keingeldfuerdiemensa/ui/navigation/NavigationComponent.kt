package m_rnd.keingeldfuerdiemensa.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import m_rnd.keingeldfuerdiemensa.entities.util.NavigationTarget
import m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.AddCanteenScreen
import m_rnd.keingeldfuerdiemensa.ui.screen.main.MainScreen
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.CanteenSettingsScreen
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.about.AboutScreen


// https://proandroiddev.com/jetpack-compose-navigation-architecture-with-viewmodels-1de467f19e1c
@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigator: Navigator
) {
    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach {
            when (it) {
                is NavigationTarget.Up -> navController.navigateUp()
                else -> navController.navigate(it.name)
            }
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = NavigationTarget.Home.name
    ) {
        composable(NavigationTarget.Home.name) {
            MainScreen(
                viewModel = hiltViewModel()
            )
        }
        composable(NavigationTarget.Settings.Canteen.name) {
            CanteenSettingsScreen(
                viewModel = hiltViewModel()
            )
        }
        composable(NavigationTarget.AddCanteen.name) {
            AddCanteenScreen(
                viewModel = hiltViewModel()
            )
        }
        composable(NavigationTarget.Settings.About.name) {
            AboutScreen(
                viewModel = hiltViewModel()
            )
        }
    }
}