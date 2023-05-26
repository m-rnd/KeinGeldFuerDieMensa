package m_rnd.keingeldfuerdiemensa.ui.components.systemui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import m_rnd.keingeldfuerdiemensa.ui.components.util.coloroverrides.translucentBackgroundColor


enum class NavigationBarType {
    TRANSPARENT,
    BACKGROUND_TRANSLUCENT
}

enum class StatusBarType {
    TRANSPARENT,
    BACKGROUND_TRANSLUCENT
}

@Composable
fun SystemUiScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    navigationBarType: NavigationBarType = NavigationBarType.TRANSPARENT,
    statusBarType: StatusBarType = StatusBarType.TRANSPARENT,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable (PaddingValues) -> Unit
) {
    Box {
        androidx.compose.material3.Scaffold(
            modifier = modifier,
            topBar = {
                val statusBarColor = when (statusBarType) {
                    StatusBarType.TRANSPARENT -> Color.Transparent
                    StatusBarType.BACKGROUND_TRANSLUCENT -> translucentBackgroundColor()
                }
                Spacer(
                    Modifier
                        .align(Alignment.TopCenter)
                        .zIndex(1f)
                        .background(statusBarColor)
                        .windowInsetsTopHeight(WindowInsets.statusBars)
                        .fillMaxWidth()
                )
                topBar()
            },
            bottomBar = {
                val navigationBarColor = when (navigationBarType) {
                    NavigationBarType.TRANSPARENT -> Color.Transparent
                    NavigationBarType.BACKGROUND_TRANSLUCENT -> translucentBackgroundColor()
                }
                Spacer(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .zIndex(1f)
                        .background(navigationBarColor)
                        .windowInsetsBottomHeight(WindowInsets.navigationBars)
                        .fillMaxWidth()
                )
                bottomBar()
            },
            contentColor = contentColor,
            containerColor = backgroundColor,
            floatingActionButton = floatingActionButton,
            floatingActionButtonPosition = floatingActionButtonPosition,
            content = content
        )
    }
}