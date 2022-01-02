package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.presentation.settings.CanteenList
import m_rnd.keingeldfuerdiemensa.ui.components.banner.InfoBanner

@Composable
fun DismissibleCanteenList(
    onCanteenDelete: (Canteen) -> Unit,
    onCanteenVisibilityChange: (Canteen) -> Unit,
    canteenResult: CanteenList.Dismissible,
    contentPadding: PaddingValues
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(canteenResult.canteens, { canteen -> canteen.id }) { canteen ->
            DismissibleCanteenListItem(
                canteen = canteen,
                onCanteenDelete = onCanteenDelete,
                onCanteenVisibilityChange = onCanteenVisibilityChange
            )
        }
        item {
            InfoBanner(contentText = stringResource(R.string.canteen_settings_info_banner))
        }
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.Black)
            ) {

            }
        }
    }

}