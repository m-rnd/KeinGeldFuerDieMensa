package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.ui.theme.AppBarElevation
import m_rnd.keingeldfuerdiemensa.ui.theme.AppBarHeight
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme
import m_rnd.keingeldfuerdiemensa.ui.theme.TranslucentSurfaceAlpha
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@Composable
fun CanteenSettingsToolbar(
    modifier: Modifier = Modifier,
    iconStart: ImageVector,
    isSortMode: Boolean,
    onNavigationIconClick: () -> Unit,
    onSortIconClick: () -> Unit,
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
                            imageVector = iconStart,
                            contentDescription = stringResource(R.string.common_content_description_navigate_up)
                        )
                    }
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = Typography.titleLarge,
                        text = title
                    )
                    IconButton(onClick = onSortIconClick) {
                        Icon(
                            painter = painterResource(if (isSortMode) R.drawable.ic_save else R.drawable.ic_baseline_sort),
                            contentDescription = stringResource(R.string.canteen_settings_content_description_toggle_sort_mode)
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
        CanteenSettingsToolbar(iconStart = Icons.Default.Close,
            onNavigationIconClick = {},
            title = "Toolbar",
            isSortMode = false,
            onSortIconClick = {}
        )
    }
}