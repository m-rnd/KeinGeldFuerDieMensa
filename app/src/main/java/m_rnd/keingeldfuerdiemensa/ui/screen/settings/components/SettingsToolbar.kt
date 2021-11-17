package m_rnd.keingeldfuerdiemensa.ui.components.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@Composable
fun SettingsToolbar(
    modifier: Modifier = Modifier,
    iconStart: ImageVector,
    onIconStartClick: () -> Unit,
    title: String
) {
    TopAppBar(
        modifier = modifier,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onIconStartClick) {
                Icon(imageVector = iconStart, contentDescription = "Toolbar")
            }
            Text(style = Typography.h6,
                color = MaterialTheme.colors.onBackground,
                text = title)
        }
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    SettingsToolbar(iconStart = Icons.Default.Close, onIconStartClick = {}, title = "Toolbar")
}