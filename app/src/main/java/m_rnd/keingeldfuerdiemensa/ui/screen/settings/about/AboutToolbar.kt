package m_rnd.keingeldfuerdiemensa.ui.screen.settings.about

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.ui.components.util.translucentSurfaceColor
import m_rnd.keingeldfuerdiemensa.ui.theme.AppBarElevation
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@Composable
fun AboutToolbar(
    onNavigationIconClick: () -> Unit,
) {
    Surface(
        color = translucentSurfaceColor(elevation = AppBarElevation),
        elevation = AppBarElevation
    ) {
        TopAppBar(
            modifier = Modifier.statusBarsPadding(),
            elevation = 0.dp,
            backgroundColor = Color.Transparent
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.common_content_description_navigate_up)
                    )
                }
                Text(
                    modifier = Modifier.weight(1f),
                    style = Typography.h6,
                    color = MaterialTheme.colors.onBackground,
                    text = stringResource(R.string.about_title)
                )
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