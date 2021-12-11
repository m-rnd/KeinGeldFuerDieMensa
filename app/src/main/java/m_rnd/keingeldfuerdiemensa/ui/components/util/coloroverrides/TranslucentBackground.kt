package m_rnd.keingeldfuerdiemensa.ui.components.util.coloroverrides

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import m_rnd.keingeldfuerdiemensa.ui.theme.TranslucentSurfaceAlpha

@Composable
fun translucentBackgroundColor(): Color {
    return MaterialTheme.colors.background.copy(
        TranslucentSurfaceAlpha
    )
}