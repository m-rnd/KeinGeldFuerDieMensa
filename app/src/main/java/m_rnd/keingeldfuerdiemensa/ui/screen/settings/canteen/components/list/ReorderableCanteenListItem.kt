package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import org.burnoutcrew.reorderable.ReorderableLazyListState
import org.burnoutcrew.reorderable.detectReorder


@Composable
fun ReorderableCanteenListItem(
    canteen: Canteen,
    state: ReorderableLazyListState,
    isDragging: Boolean
) {
    val elevation by animateDpAsState(if (isDragging) 4.dp else 0.dp)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp),
        shadowElevation = elevation,
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f),
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis,
                text = canteen.name
            )

            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                Icon(
                    modifier = Modifier.detectReorder(state),
                    imageVector = Icons.Default.DragHandle,
                    contentDescription = stringResource(R.string.canteen_settings_content_description_sort_canteen)
                )
            }
        }
    }
}