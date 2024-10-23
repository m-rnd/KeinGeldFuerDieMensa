package m_rnd.keingeldfuerdiemensa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import m_rnd.keingeldfuerdiemensa.datasource.datastore.USER_PREFERENCE_DEFAULT_THEME_MODE
import m_rnd.keingeldfuerdiemensa.datasource.datastore.USER_PREFERENCE_DEFAULT_USE_DYNAMIC_COLORS
import m_rnd.keingeldfuerdiemensa.entities.ThemeMode
import m_rnd.keingeldfuerdiemensa.repository.UserPreferenceRepository
import m_rnd.keingeldfuerdiemensa.ui.navigation.NavigationComponent
import m_rnd.keingeldfuerdiemensa.ui.navigation.Navigator
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var userPreferences: UserPreferenceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val dynamicColorsPref by userPreferences.dynamicColorsEnabled.collectAsState(initial = USER_PREFERENCE_DEFAULT_USE_DYNAMIC_COLORS)
            val themeModePref by userPreferences.themeMode.collectAsState(initial = USER_PREFERENCE_DEFAULT_THEME_MODE)
            val darkTheme = when (themeModePref) {
                ThemeMode.LIGHT -> false
                ThemeMode.DARK -> true
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
            }

            AppTheme(
                userPrefDynamicColors = dynamicColorsPref,
                darkTheme = darkTheme
            ) {
                NavigationComponent(rememberNavController(), navigator)

                val useDarkIcons = !darkTheme
                val systemUiController = rememberSystemUiController()

                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons,
                    false
                )
            }
        }
    }
}