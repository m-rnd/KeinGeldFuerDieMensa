package m_rnd.keingeldfuerdiemensa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import m_rnd.keingeldfuerdiemensa.ui.navigation.NavigationComponent
import m_rnd.keingeldfuerdiemensa.ui.navigation.Navigator
import m_rnd.keingeldfuerdiemensa.ui.theme.ComposeTestTheme
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTestTheme {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    NavigationComponent(rememberNavController(), navigator)

                    val useDarkIcons = MaterialTheme.colors.isLight
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
}