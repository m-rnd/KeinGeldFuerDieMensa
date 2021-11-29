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
import m_rnd.keingeldfuerdiemensa.entities.util.getTargetName
import m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.AddCanteenScreen
import m_rnd.keingeldfuerdiemensa.ui.screen.main.MainScreen
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.SettingsScreen


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
                is NavigationTarget.Home,
                is NavigationTarget.AddCanteen,
                is NavigationTarget.Settings.Canteen -> navController.navigate(it.getTargetName())
            }
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = getTargetName<NavigationTarget.Home>()
    ) {
        composable(getTargetName<NavigationTarget.Home>()) {
            MainScreen(
                viewModel = hiltViewModel()
            )
        }
        composable(getTargetName<NavigationTarget.Settings.Canteen>()) {
            SettingsScreen(
                viewModel = hiltViewModel()
            )
        }
        composable(getTargetName<NavigationTarget.AddCanteen>()) {
            AddCanteenScreen(
                viewModel = hiltViewModel()
            )
        }
    }
}