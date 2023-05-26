package m_rnd.keingeldfuerdiemensa.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    primaryColorLightTheme: Color = Purple,
    primaryColorDarkTheme: Color = PurpleLight,
    content: @Composable () -> Unit
) {
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = when {
        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> darkColorScheme(primary = primaryColorDarkTheme)
        else -> lightColorScheme(primaryColorLightTheme)
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}