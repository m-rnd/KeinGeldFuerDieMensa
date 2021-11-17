package m_rnd.keingeldfuerdiemensa.ui.screen.settings.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@Composable
fun SettingsSectionHeader(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier.padding(vertical = 8.dp),
        style = Typography.h5,
        color = MaterialTheme.colors.primary,
        text = text)
}