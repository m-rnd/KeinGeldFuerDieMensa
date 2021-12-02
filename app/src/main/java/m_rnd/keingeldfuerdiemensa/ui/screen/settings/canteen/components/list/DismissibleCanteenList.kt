package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.presentation.settings.CanteenList

@Composable
fun DismissibleCanteenList(
    onCanteenDelete: (Canteen) -> Unit,
    onCanteenVisibilityChange: (Canteen) -> Unit,
    canteenResult: CanteenList.Dismissible
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(canteenResult.canteens, {canteen -> canteen.id}) { canteen ->
            DismissibleCanteenListItem(
                canteen = canteen,
                onCanteenDelete = onCanteenDelete,
                onCanteenVisibilityChange = onCanteenVisibilityChange
            )
        }
    }

}