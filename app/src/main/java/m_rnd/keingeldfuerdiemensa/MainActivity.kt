package m_rnd.keingeldfuerdiemensa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import m_rnd.keingeldfuerdiemensa.presentation.MainViewModel
import m_rnd.keingeldfuerdiemensa.ui.dialog.canteendetail.CanteenDetailDialog
import m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.AddCanteenScreen
import m_rnd.keingeldfuerdiemensa.ui.screen.main.MainScreen
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.SettingsScreen
import m_rnd.keingeldfuerdiemensa.ui.theme.ComposeTestTheme
import timber.log.Timber
import timber.log.Timber.DebugTree

enum class NavigationDestination {
    MAIN,
    SETTINGS,
    ADD_CANTEEN,
    DIALOG_CANTEEN_DETAIL
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                val navController = rememberNavController()
                NavHost(navController = navController,
                    startDestination = NavigationDestination.MAIN.name) {
                    composable(NavigationDestination.MAIN.name) {
                        MainScreen(
                            viewModel = hiltViewModel(),
                            navController = navController
                        )
                    }
                    composable(NavigationDestination.SETTINGS.name) {
                        SettingsScreen(
                            viewModel = hiltViewModel(),
                            navController = navController
                        )
                    }
                    composable(NavigationDestination.ADD_CANTEEN.name) {
                        AddCanteenScreen(
                            viewModel = hiltViewModel(),
                            navController = navController
                        )
                    }
                    dialog(NavigationDestination.DIALOG_CANTEEN_DETAIL.name) {
                        CanteenDetailDialog(
                            viewModel = hiltViewModel()
                        )
                    }
                }
            }
        }
    }
}


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTestTheme {
        MainScreen(MainViewModel(), rememberNavController())
    }
}