package m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.presentation.AddCanteenViewModel
import m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components.AddCanteenToolbar
import m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components.CanteenSearchResultList
import m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.namedialog.CanteenNameDialog

@Composable
fun AddCanteenScreen(viewModel: AddCanteenViewModel) {
    Content(
        onNavigateUp = { viewModel.navigateUp() },
        onCanteenInputChanged = { viewModel.onCanteenInputChanged(it) },
        onCanteenClicked = {
            viewModel.onCanteenClicked(it)
        },
        isLoading = viewModel.isLoading.value,
        canteenSearchInput = viewModel.canteenSearchInput.value,
        filteredCanteens = viewModel.filteredCanteens.value
    )
    if (viewModel.nameDialogShowing) {
        LocalFocusManager.current.clearFocus()
        CanteenNameDialog(
            canteen = viewModel.selectedCanteen ?: return,
            onDialogResult = { viewModel.onNameDialogResult(it) }
        )
    }
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