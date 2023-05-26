package m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.ui.components.banner.InfoBanner
import m_rnd.keingeldfuerdiemensa.ui.components.util.pluralResource


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CanteenSearchResultList(
    contentPadding: PaddingValues,
    onCanteenClicked: (CanteenSearchResult) -> Unit,
    showResultCount: Boolean,
    filteredCanteens: List<CanteenSearchResult>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .imeNestedScroll(),
        contentPadding = contentPadding,
        content = {

            if (filteredCanteens.isEmpty()) {
                item { InfoBanner(contentText = stringResource(R.string.add_canteen_search_result_info_label)) }
            }

            items(filteredCanteens) { canteen ->
                CanteenSearchResultListItem(
                    modifier = Modifier.clickable {
                        onCanteenClicked(canteen)
                    },
                    canteen = canteen
                )
            }

            if (showResultCount) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        textAlign = TextAlign.Center,
                        text = pluralResource(
                            id = R.plurals.add_canteen_search_result_amount,
                            quantity = filteredCanteens.size,
                            formatArgs = arrayOf(filteredCanteens.size)
                        )
                    )
                }
            }
        }
    )
}