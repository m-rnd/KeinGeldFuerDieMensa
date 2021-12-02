package m_rnd.keingeldfuerdiemensa.ui.components.toolbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
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
    TopAppBar(
        modifier = modifier,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = iconStart, contentDescription = "Toolbar")
            }
            Text(
                modifier = Modifier.weight(1f),
                style = Typography.h6,
                color = MaterialTheme.colors.onBackground,
                text = title
            )
            IconButton(onClick = onSortIconClick) {
                Icon(
                    painter = painterResource(if (isSortMode) R.drawable.ic_save else R.drawable.ic_baseline_sort),
                    contentDescription = "sort"
                )
            }
        }
    }
}

@Preview
@Composable
fun MensaSettingsToolbarPreview() {
    CanteenSettingsToolbar(iconStart = Icons.Default.Close,
        onNavigationIconClick = {},
        title = "Toolbar",
        isSortMode = false,
        onSortIconClick = {}
    )
}