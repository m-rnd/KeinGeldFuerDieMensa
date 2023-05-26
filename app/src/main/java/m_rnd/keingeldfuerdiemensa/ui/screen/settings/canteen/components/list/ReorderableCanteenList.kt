package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.presentation.settings.CanteenList
import m_rnd.keingeldfuerdiemensa.ui.components.banner.InfoBanner
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun ReorderableCanteenList(
    moveCanteen: (Int, Int) -> Unit,
    canteenResult: CanteenList.Reorderable,
    contentPadding: PaddingValues
) {
    val reorderableState =
        rememberReorderableLazyListState(onMove = { from, to -> moveCanteen(from.index, to.index) })
    LazyColumn(
        state = reorderableState.listState,
        contentPadding = contentPadding,
        modifier = Modifier
            .fillMaxWidth()
            .reorderable(
                reorderableState
            ),
    ) {
        items(canteenResult.canteens, { it.id }) { canteen ->
            ReorderableCanteenListItem(
                canteen = canteen,
                state = reorderableState
            )
        }
        item {
            InfoBanner(contentText = stringResource(R.string.canteen_settings_info_banner))
        }
    }
}