package m_rnd.keingeldfuerdiemensa.ui.components.util

import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import m_rnd.keingeldfuerdiemensa.ui.theme.TranslucentSurfaceAlpha


@Composable
fun translucentSurfaceColor(elevation: Dp): Color {
    val elevationOverlay = LocalElevationOverlay.current
    val absoluteElevation = LocalAbsoluteElevation.current + elevation
    val color = MaterialTheme.colors.secondary.copy(TranslucentSurfaceAlpha)
    return elevationOverlay?.apply(color, absoluteElevation) ?: color
}