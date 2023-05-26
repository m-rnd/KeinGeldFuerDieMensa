package m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components

import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult

@Composable
fun CanteenSearchResultListItem(modifier: Modifier = Modifier, canteen: CanteenSearchResult) {
    ListItem(
        modifier = modifier,
        overlineContent = { Text(canteen.city) },
        supportingContent = { Text(canteen.address) },
        headlineContent = { Text(canteen.name) }
    )
}

@Preview
@Composable
fun CanteenSearchResultListItemPreview() {
    CanteenSearchResultListItem(
        canteen = CanteenSearchResult(
            1,
            "Mensa",
            "Leipzig",
            "Musterstraße 64, 04109 Leipzig",
            listOf()
        )
    )
}