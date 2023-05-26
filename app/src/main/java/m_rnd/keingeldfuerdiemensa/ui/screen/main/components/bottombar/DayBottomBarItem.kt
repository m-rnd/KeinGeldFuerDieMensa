package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.bottombar

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.common.extensions.toVisualDate
import m_rnd.keingeldfuerdiemensa.common.extensions.toVisualDay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DayItem(timestamp: Long, isSelected: Boolean) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = MaterialTheme.typography.titleSmall, text = timestamp.toVisualDay(
                stringResource(id = R.string.common_today),
                stringResource(id = R.string.common_tomorrow)
            )
        )
        AnimatedVisibility(
            visible = isSelected,
            enter = scaleIn() + expandVertically(),
            exit = scaleOut() + shrinkVertically()
        ) {
            Text(style = MaterialTheme.typography.bodySmall, text = timestamp.toVisualDate())
        }
    }
}

@Composable
fun SettingsItem() {
    Icon(
        imageVector = Icons.Outlined.Settings,
        contentDescription = stringResource(R.string.main_content_description_settings_icon)
    )
}