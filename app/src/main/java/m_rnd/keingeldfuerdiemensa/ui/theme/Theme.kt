package m_rnd.keingeldfuerdiemensa.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import m_rnd.keingeldfuerdiemensa.datasource.datastore.USER_PREFERENCE_DEFAULT_USE_DYNAMIC_COLORS

@Composable
fun AppTheme(
    userPrefDynamicColors: Boolean = USER_PREFERENCE_DEFAULT_USE_DYNAMIC_COLORS,
    darkTheme: Boolean = isSystemInDarkTheme(),
    primaryColorLightTheme: Color = Purple,
    primaryColorDarkTheme: Color = PurpleLight,
    content: @Composable () -> Unit
) {
    val dynamicColor = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) && userPrefDynamicColors
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