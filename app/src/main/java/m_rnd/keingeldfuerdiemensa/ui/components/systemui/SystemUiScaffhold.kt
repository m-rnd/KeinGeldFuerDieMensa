package m_rnd.keingeldfuerdiemensa.ui.components.systemui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.ui.LocalScaffoldPadding
import com.google.accompanist.insets.ui.Scaffold
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
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = false,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    contentPadding: PaddingValues = LocalScaffoldPadding.current,
    navigationBarType: NavigationBarType = NavigationBarType.TRANSPARENT,
    statusBarType: StatusBarType = StatusBarType.TRANSPARENT,
    content: @Composable (PaddingValues) -> Unit
) {
    Box {
        Scaffold(
            modifier = modifier.zIndex(0f),
            scaffoldState = scaffoldState,
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
                        .statusBarsHeight()
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
                        .navigationBarsHeight()
                        .fillMaxWidth()
                )
                bottomBar()
            },
            snackbarHost = snackbarHost,
            floatingActionButton = floatingActionButton,
            floatingActionButtonPosition = floatingActionButtonPosition,
            isFloatingActionButtonDocked = isFloatingActionButtonDocked,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            contentPadding = contentPadding,
            content = content
        )
    }
}