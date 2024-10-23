package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.presentation.settings.CanteenList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DismissibleCanteenList(
    onCanteenDelete: (Canteen) -> Unit,
    onCanteenVisibilityChange: (Canteen) -> Unit,
    canteenResult: CanteenList.Dismissible,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 4.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(canteenResult.canteens, key = { canteen -> canteen }) { canteen ->
            DismissibleCanteenListItem(
                canteen = canteen,
                onCanteenDelete = onCanteenDelete,
                onCanteenVisibilityChange = onCanteenVisibilityChange,
                modifier = Modifier.animateItemPlacement()
            )
        }
    }
}