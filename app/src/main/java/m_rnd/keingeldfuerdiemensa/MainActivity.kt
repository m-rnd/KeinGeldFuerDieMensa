package m_rnd.keingeldfuerdiemensa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
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

        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                NavigationComponent(rememberNavController(), navigator)
            }
        }
    }
}