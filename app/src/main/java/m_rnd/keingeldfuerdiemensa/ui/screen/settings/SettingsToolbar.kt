package m_rnd.keingeldfuerdiemensa.ui.screen.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.ui.theme.*

@Composable
fun SettingsToolbar(
    modifier: Modifier = Modifier,
    onNavigationIconClick: () -> Unit,
    onInfoIconClick: () -> Unit,
    title: String
) {
    Surface(
        tonalElevation = AppBarElevation,
        modifier = modifier.alpha(TranslucentSurfaceAlpha)
    ) {
        TopAppBar(
            modifier = modifier
                .statusBarsPadding()
                .height(AppBarHeight),
            elevation = 0.dp,
            backgroundColor = Color.Transparent
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimaryContainer) {
                    IconButton(onClick = onNavigationIconClick) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = stringResource(R.string.common_content_description_navigate_up)
                        )
                    }
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = Typography.titleLarge,
                        text = title
                    )
                    IconButton(onClick = onInfoIconClick) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.settings_content_description_info)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CanteenSettingsToolbarPreview() {
    AppTheme {
        SettingsToolbar(
            onNavigationIconClick = {},
            title = "Toolbar",
            onInfoIconClick = {}
        )
    }
}