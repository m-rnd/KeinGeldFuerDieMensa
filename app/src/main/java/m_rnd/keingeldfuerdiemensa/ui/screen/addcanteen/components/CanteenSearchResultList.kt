package m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.ui.components.util.pluralResource

@Composable
fun CanteenSearchResultList(
    onCanteenClicked: (CanteenSearchResult) -> Unit,
    showResultCount: Boolean,
    filteredCanteens: List<CanteenSearchResult>
) {
    LazyColumn(
        content = {
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