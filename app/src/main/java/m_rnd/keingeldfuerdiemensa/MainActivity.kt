package m_rnd.keingeldfuerdiemensa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import m_rnd.keingeldfuerdiemensa.presentation.MainViewModel
import m_rnd.keingeldfuerdiemensa.presentation.SettingsViewModel
import m_rnd.keingeldfuerdiemensa.ui.screen.main.MainScreen
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.SettingsScreen
import m_rnd.keingeldfuerdiemensa.ui.theme.ComposeTestTheme
import timber.log.Timber
import timber.log.Timber.DebugTree

enum class NavigationDestination {
    MAIN,
    SETTINGS
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {

        val mainViewModel: MainViewModel by viewModels()
        val settingsViewModel: SettingsViewModel by viewModels()

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
                            viewModel = mainViewModel,
                            navController = navController
                        )
                    }
                    composable(NavigationDestination.SETTINGS.name) {
                        SettingsScreen(
                            viewModel = settingsViewModel,
                            navController = navController
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