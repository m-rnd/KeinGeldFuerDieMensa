package m_rnd.keingeldfuerdiemensa.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    primaryColorLightTheme: Color = Purple,
    primaryColorDarkTheme: Color = PurpleLight,
    content: @Composable() () -> Unit
) {
    /*
    primary - color of all ui elements
    secondary - here: color of toolbar and bottombar (tintedSurface)
     */
    val colors = if (darkTheme) {
        darkColors(
            primary = primaryColorDarkTheme,
            secondary = Color(
                ColorUtils.blendARGB(
                    primaryColorLightTheme.toArgb(),
                    Color(0xFF121212).toArgb(),// see default surface Color
                    0.95f
                )
            )
        )
    } else {
        lightColors(
            primary = primaryColorLightTheme,
            secondary = Color(
                ColorUtils.blendARGB(
                    primaryColorLightTheme.toArgb(),
                    Color.White.toArgb(),
                    0.95f
                )
            )
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}