package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import m_rnd.keingeldfuerdiemensa.presentation.settings.CanteenList
import org.burnoutcrew.reorderable.rememberReorderState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun ReorderableCanteenList(
    moveCanteen: (Int, Int) -> Unit,
    canteenResult: CanteenList.Reorderable
) {
    val reorderableState = rememberReorderState()
    LazyColumn(
        state = reorderableState.listState,
        modifier = Modifier
            .fillMaxWidth()
            .reorderable(
                reorderableState,
                { from, to -> moveCanteen(from.index, to.index) }
            ),
    ) {
        items(canteenResult.canteens, { it.id }) { canteen ->
            ReorderableCanteenListItem(
                canteen = canteen,
                state = reorderableState
            )
        }
    }
}