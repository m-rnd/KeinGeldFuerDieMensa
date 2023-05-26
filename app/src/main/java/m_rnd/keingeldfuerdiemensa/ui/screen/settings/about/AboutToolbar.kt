package m_rnd.keingeldfuerdiemensa.ui.screen.settings.about

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun AboutToolbar(
    onNavigationIconClick: () -> Unit,
) {
    Surface(
        tonalElevation = AppBarElevation,
        modifier = Modifier.alpha(TranslucentSurfaceAlpha)
    ) {
        TopAppBar(
            modifier = Modifier
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
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.common_content_description_navigate_up)
                        )
                    }
                    Text(
                        modifier = Modifier.weight(1f),
                        style = Typography.titleLarge,
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.about_title)
                    )
                    Spacer(modifier = Modifier.size(48.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun AboutToolbarPreview() {
    AppTheme {
        AboutToolbar(
            onNavigationIconClick = {},
        )
    }
}