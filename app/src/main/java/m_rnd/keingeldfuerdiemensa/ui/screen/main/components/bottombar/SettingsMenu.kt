package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.bottombar

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import m_rnd.keingeldfuerdiemensa.presentation.MainMenuItem

@Composable
fun SettingsMenu(
    isVisible: Boolean,
    onMenuItemClick: (MainMenuItem) -> Unit,
    onDismiss: () -> Unit
) {
    DropdownMenu(expanded = isVisible, onDismissRequest = onDismiss) {
        MainMenuItem.values().forEach { menuItem ->
            DropdownMenuItem(onClick = { onMenuItemClick(menuItem) }) {
                Text(stringResource(id = menuItem.displayName))
            }
        }
    }
}