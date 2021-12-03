package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.daybar

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import m_rnd.keingeldfuerdiemensa.R

@Composable
fun SettingsItem() {
    Icon(imageVector = Icons.Outlined.Settings, contentDescription = stringResource(R.string.main_content_description_settings_icon))
}