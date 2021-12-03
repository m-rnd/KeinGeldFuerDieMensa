package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import org.burnoutcrew.reorderable.ReorderableState
import org.burnoutcrew.reorderable.detectReorder
import org.burnoutcrew.reorderable.draggedItem


@Composable
fun ReorderableCanteenListItem(
    canteen: Canteen,
    state: ReorderableState
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .draggedItem(state.offsetByKey(canteen.id))
            .padding(vertical = 1.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                modifier = Modifier.detectReorder(state),
                painter = painterResource(R.drawable.ic_baseline_drag_handle),
                contentDescription = stringResource(R.string.canteen_settings_content_description_sort_canteen),
                tint = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
            )

            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                style = MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis,
                text = canteen.name
            )
        }
    }
}