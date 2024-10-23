package m_rnd.keingeldfuerdiemensa.ui.screen.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.ui.screen.main.SheetDragHandlePadding
import m_rnd.keingeldfuerdiemensa.ui.screen.main.SheetDragHandlePillHeight

@Composable
fun BottomSheetDragHandle() {
    Surface(
        modifier = Modifier.padding(SheetDragHandlePadding),
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(Modifier.size(32.dp, SheetDragHandlePillHeight))
    }
}