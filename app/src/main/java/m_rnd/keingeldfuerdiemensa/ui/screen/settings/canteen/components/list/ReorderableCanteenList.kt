package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.presentation.settings.CanteenList
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun ReorderableCanteenList(
    moveCanteen: (Int, Int) -> Unit,
    canteenResult: CanteenList.Reorderable,
    modifier: Modifier = Modifier,
) {
    val reorderableState =
        rememberReorderableLazyListState(onMove = { from, to -> moveCanteen(from.index, to.index) })
    LazyColumn(
        contentPadding = PaddingValues(vertical = 4.dp),
        state = reorderableState.listState,
        modifier = modifier
            .fillMaxWidth()
            .reorderable(
                reorderableState
            ),
    ) {
        items(canteenResult.canteens, { it }) { canteen ->
            ReorderableItem(reorderableState, key = canteen) { isDragging ->
                ReorderableCanteenListItem(
                    canteen = canteen,
                    state = reorderableState,
                    isDragging = isDragging
                )
            }
        }
    }
}