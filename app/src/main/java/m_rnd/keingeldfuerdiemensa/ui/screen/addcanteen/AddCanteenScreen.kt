package m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.presentation.AddCanteenViewModel
import m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components.AddCanteenToolbar
import m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components.CanteenSearchResultList
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@Composable
fun AddCanteenScreen(viewModel: AddCanteenViewModel, navController: NavController) {
    Content(
        onNavigateUp = { navController.navigateUp() },
        onCanteenInputChanged = { viewModel.onCanteenInputChanged(it) },
        onCanteenClicked = {
            viewModel.onCanteenClicked(it)
            navController.navigateUp()
        },
        isLoading = viewModel.isLoading.value,
        canteenSearchInput = viewModel.canteenSearchInput.value,
        filteredCanteens = viewModel.filteredCanteens.value
    )
}

@Composable
private fun Content(
    onNavigateUp: () -> Unit,
    onCanteenInputChanged: (TextFieldValue) -> Unit,
    onCanteenClicked: (CanteenSearchResult) -> Unit,
    isLoading: Boolean,
    canteenSearchInput: TextFieldValue,
    filteredCanteens: List<CanteenSearchResult>
) {
    Scaffold(topBar = {
        AddCanteenToolbar(
            onNavigateUp = onNavigateUp,
            onCanteenInputChanged = onCanteenInputChanged,
            canteenSearchInput = canteenSearchInput,
            isLoading = isLoading
        )
    }) {
        Column(
            modifier = Modifier.padding(it)
        ) {

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                CanteenSearchResultList(
                    onCanteenClicked = onCanteenClicked,
                    showResultCount = canteenSearchInput.text.length > 1,
                    filteredCanteens = filteredCanteens
                )
            }
        }
    }
}

@Preview
@Composable
fun AddCanteenScreenPreview() {
    Content(
        onNavigateUp = { },
        onCanteenInputChanged = { },
        onCanteenClicked = { },
        isLoading = false,
        canteenSearchInput = TextFieldValue("asdf"),
        filteredCanteens = listOf(
            CanteenSearchResult(
                1,
                "Mensa",
                "Leipzig",
                "Innenstadt",
                listOf()
            )
        )
    )
}