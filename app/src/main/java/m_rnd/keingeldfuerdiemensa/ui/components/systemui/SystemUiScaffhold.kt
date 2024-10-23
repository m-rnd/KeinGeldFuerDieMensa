package m_rnd.keingeldfuerdiemensa.ui.components.systemui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import m_rnd.keingeldfuerdiemensa.ui.components.util.coloroverrides.translucentBackgroundColor
import m_rnd.keingeldfuerdiemensa.ui.components.util.dpToPx
import kotlin.math.max


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
        Scaffold(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SystemUiBottomSheetScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(backgroundColor),
    collapsedSheetCornerRadius: Dp,
    collapsedSheetHeight: Dp,
    sheetContent: @Composable (() -> Float) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val systemBarBottomHeight = WindowInsets.systemBars.getBottom(LocalDensity.current)
    val systemBarTopHeight = WindowInsets.systemBars.getTop(LocalDensity.current)
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp.dpToPx()
    var bottomSheetOffset by remember { mutableFloatStateOf(0f) }
    val collapsedSheetHeightPx = collapsedSheetHeight.dpToPx()
    val systemBarHeight = WindowInsets.systemBars.getBottom(LocalDensity.current).dp

    val systemBarTopProgress = remember(bottomSheetOffset) {
        (1 - bottomSheetOffset / systemBarTopHeight).takeUnless { it.isNaN() }?.coerceIn(0f, 1f)
            ?: 0f
    }

    val bottomSheetProgress = {
        ((bottomSheetOffset)) / (screenHeight + systemBarTopHeight + systemBarBottomHeight - collapsedSheetHeightPx)
    }

    BottomSheetScaffold(
        modifier = modifier.onGloballyPositioned {
            bottomSheetOffset = max(0f, scaffoldState.bottomSheetState.requireOffset())
        },
        scaffoldState = scaffoldState,
        contentColor = contentColor,
        sheetDragHandle = {},
        sheetPeekHeight = collapsedSheetHeight,
        content = content,
        sheetShape = RoundedCornerShape(size = collapsedSheetCornerRadius * (1 - systemBarTopProgress)),
        sheetContent = { Box { sheetContent(bottomSheetProgress) } }
    )
}
