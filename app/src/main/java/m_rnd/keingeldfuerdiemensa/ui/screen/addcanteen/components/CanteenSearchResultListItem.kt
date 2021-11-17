package m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CanteenSearchResultListItem(modifier: Modifier = Modifier, canteen: CanteenSearchResult) {
    ListItem(
        modifier = modifier,
        overlineText = {Text(canteen.city)},
        secondaryText = {Text(canteen.address)},
        text = { Text(canteen.name)}
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