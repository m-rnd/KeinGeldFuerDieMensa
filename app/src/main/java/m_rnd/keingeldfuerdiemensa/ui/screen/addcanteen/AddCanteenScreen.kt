package m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.util.UiState
import m_rnd.keingeldfuerdiemensa.presentation.AddCanteenViewModel
import m_rnd.keingeldfuerdiemensa.ui.components.banner.ErrorBanner
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.StatusBarType
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.SystemUiScaffold
import m_rnd.keingeldfuerdiemensa.ui.components.util.LoadingIndicator
import m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components.BottomSearchBar
import m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components.CanteenSearchResultList
import m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.namedialog.CanteenNameDialog
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme

@Composable
fun AddCanteenScreen(viewModel: AddCanteenViewModel) {
    Content(
        onNavigateUp = { viewModel.navigateUp() },
        onCanteenInputChanged = { viewModel.onCanteenInputChanged(it) },
        onCanteenClicked = {
            viewModel.onCanteenClicked(it)
        },
        uiState = viewModel.uiState,
        canteenSearchInput = remember { viewModel.canteenSearchInput }.value,
        filteredCanteens = remember { viewModel.filteredCanteens }.value
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
    uiState: UiState,
    canteenSearchInput: TextFieldValue,
    filteredCanteens: List<CanteenSearchResult>
) {
    SystemUiScaffold(
        bottomBar = {
            BottomSearchBar(
                onNavigateUp = onNavigateUp,
                onCanteenInputChanged = onCanteenInputChanged,
                searchEnabled = uiState == UiState.Ready,
                canteenSearchInput = canteenSearchInput

            )
        },
        statusBarType = StatusBarType.BACKGROUND_TRANSLUCENT
    ) { contentPadding ->
        when (uiState) {
            is UiState.Loading -> {
                LoadingIndicator(Modifier.padding(contentPadding))
            }
            is UiState.Error -> {
                ErrorBanner(
                    errorReason = uiState.reason,
                    modifier = Modifier.padding(contentPadding)
                )
            }
            is UiState.Ready -> {
                CanteenSearchResultList(
                    contentPadding = contentPadding,
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
    AppTheme {
        Content(
            onNavigateUp = { },
            onCanteenInputChanged = { },
            onCanteenClicked = { },
            uiState = UiState.Ready,
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
}