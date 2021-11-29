package m_rnd.keingeldfuerdiemensa.ui.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.presentation.settings.CanteenList
import m_rnd.keingeldfuerdiemensa.presentation.settings.CanteenSettingsViewModel
import m_rnd.keingeldfuerdiemensa.ui.components.toolbar.CanteenSettingsToolbar
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.AddCanteenFAB
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list.DismissibleCanteenList
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list.ReorderableCanteenList

@Composable
fun SettingsScreen(viewModel: CanteenSettingsViewModel) {
    Content(
        onNavigateUp = { viewModel.navigateUp() },
        onAddCanteenClick = { viewModel.navigateToAddCanteenScreen() },
        onCanteenDelete = { viewModel.deleteCanteen(it) },
        onCanteenVisibilityChange = { viewModel.toggleCanteenVisibility(it) },
        canteenResult = viewModel.canteensFlow.collectAsState(initial = CanteenList.EmptyList).value,
        isSortMode = viewModel.isSortEnabled.value,
        onSortIconClick = { viewModel.toggleSortMode() },
        moveCanteen = { from, to -> viewModel.moveCanteen(from, to) }
    )
}

@Composable
private fun Content(
    onNavigateUp: () -> Unit,
    onAddCanteenClick: () -> Unit,
    onCanteenDelete: (Canteen) -> Unit,
    onCanteenVisibilityChange: (Canteen) -> Unit,
    onSortIconClick: () -> Unit,
    moveCanteen: (Int, Int) -> Unit,
    isSortMode: Boolean,
    canteenResult: CanteenList
) {
    Scaffold(
        topBar = {
            CanteenSettingsToolbar(
                iconStart = Icons.Default.ArrowBack,
                title = stringResource(R.string.canteen_settings_title),
                isSortMode = isSortMode,
                onNavigationIconClick = onNavigateUp,
                onSortIconClick = onSortIconClick
            )
        },
        floatingActionButton = {
            AddCanteenFAB(isVisible = !isSortMode, onFABClick = onAddCanteenClick)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(vertical = 16.dp)
        ) {
            when (canteenResult) {
                is CanteenList.Reorderable -> {
                    ReorderableCanteenList(
                        moveCanteen = moveCanteen,
                        canteenResult = canteenResult
                    )
                }
                is CanteenList.Dismissible -> {
                    DismissibleCanteenList(
                        onCanteenDelete = onCanteenDelete,
                        onCanteenVisibilityChange = onCanteenVisibilityChange,
                        canteenResult = canteenResult
                    )
                }
                is CanteenList.EmptyList -> {
                    //Todo add info box
                    Text("leere Liste")
                }
            }
        }
    }
}


@Preview
@Composable
fun SettingsScreenPreview() {
    Content(
        onNavigateUp = {},
        onAddCanteenClick = {},
        canteenResult = CanteenList.EmptyList,
        onCanteenDelete = {},
        onCanteenVisibilityChange = {},
        isSortMode = false,
        onSortIconClick = {},
        moveCanteen = { _, _ -> }
    )
}