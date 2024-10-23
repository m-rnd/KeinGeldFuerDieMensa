package m_rnd.keingeldfuerdiemensa.ui.screen.settings.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.color.DynamicColors
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.ThemeMode
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.themeSettingItems(
    selectedThemeMode: ThemeMode,
    useDynamicColors: Boolean,
    onThemeModeChange: (ThemeMode) -> Unit,
    onDynamicColorSelect: (Boolean) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            style = Typography.titleMedium,
            text = stringResource(R.string.theme_settings_title)
        )
    }

    item {
        SingleChoiceSegmentedButtonRow(Modifier.fillMaxWidth()) {
            ThemeMode.entries.forEachIndexed { index, themeMode ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = ThemeMode.entries.size
                    ),
                    selected = selectedThemeMode == themeMode,
                    onClick = { onThemeModeChange(themeMode) }) {
                    Text(text = themeName(themeMode))
                }
            }
        }
    }


    if (DynamicColors.isDynamicColorAvailable()) {
        item {
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val clickableTextInteractionSource = remember { MutableInteractionSource() }
                Text(
                    text = stringResource(id = R.string.theme_settings_use_dynamic_colors),
                    modifier = Modifier
                        .clickable(
                            interactionSource = clickableTextInteractionSource,
                            indication = null,
                            onClick = { onDynamicColorSelect(!useDynamicColors) })
                        .weight(1f),
                    style = Typography.titleSmall,
                )
                Switch(
                    checked = useDynamicColors,
                    onCheckedChange = onDynamicColorSelect,
                    interactionSource = clickableTextInteractionSource
                )
            }
        }
    }
}

@Composable
private fun themeName(themeMode: ThemeMode) = when (themeMode) {
    ThemeMode.LIGHT -> stringResource(R.string.theme_settings_mode_light)
    ThemeMode.DARK -> stringResource(R.string.theme_settings_mode_dark)
    ThemeMode.SYSTEM -> stringResource(R.string.theme_settings_mode_system)
}

@Preview
@Composable
fun ThemeSettingItemsPreview() {
    AppTheme {
        LazyColumn(Modifier.padding(horizontal = 16.dp)) {
            themeSettingItems(
                selectedThemeMode = ThemeMode.DARK,
                useDynamicColors = false,
                onThemeModeChange = {},
                onDynamicColorSelect = {}
            )
        }
    }
}