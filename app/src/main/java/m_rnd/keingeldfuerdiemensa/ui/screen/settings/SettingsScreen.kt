package m_rnd.keingeldfuerdiemensa.ui.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import m_rnd.keingeldfuerdiemensa.NavigationDestination
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.FlowState
import m_rnd.keingeldfuerdiemensa.presentation.SettingsViewModel
import m_rnd.keingeldfuerdiemensa.ui.components.toolbar.SettingsToolbar
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.components.SettingsSectionHeader
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.components.sections.SettingsSectionCanteen

@Composable
fun SettingsScreen(viewModel: SettingsViewModel, navController: NavController) {
    Content(
        onNavigateUp = { navController.navigateUp() },
        onAddCanteenClick = { navController.navigate(NavigationDestination.ADD_CANTEEN.name) },
        canteenResult = viewModel.getCanteens().collectAsState(initial = FlowState.Loading).value
    )
    Scaffold(topBar = {
        SettingsToolbar(
            iconStart = Icons.Default.ArrowBack,
            title = stringResource(id = R.string.settings_title),
            onIconStartClick = { navController.navigateUp() }
        )
    }) {

        Column(modifier = Modifier
            .padding(it)
            .padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                SettingsSectionHeader(text = stringResource(id = R.string.settings_section_header_canteens))
                IconButton(onClick = { navController.navigate(NavigationDestination.ADD_CANTEEN.name) }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                }
            }
            val mensas = viewModel.getCanteens().collectAsState(initial = FlowState.Loading)
            //TODO add loading state
            when (val v = mensas.value) {
                is FlowState.Error -> {
                    Text(text = v.reason.additionalInfo)
                }
                is FlowState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
                        v.data.forEach { mensa ->
                            item {
                                Text(modifier = Modifier.fillMaxWidth(), text = mensa.name)
                            }
                        }
                    })
                }
            }
        }
    }
}

@Composable
private fun Content(
    onNavigateUp: () -> Unit,
    onAddCanteenClick: () -> Unit,
    canteenResult: FlowState<List<Canteen>>
){
    Scaffold(topBar = {
        SettingsToolbar(
            iconStart = Icons.Default.ArrowBack,
            title = stringResource(id = R.string.settings_title),
            onIconStartClick = onNavigateUp
        )
    }) {

        Column(modifier = Modifier
            .padding(it)
            .padding(16.dp)) {
            
            SettingsSectionCanteen(
                onAddCanteenClick = onAddCanteenClick,
                canteenResult = canteenResult
            )
        }
    }
}


@Preview
@Composable
fun SettingsScreenPreview() {
    Content(
        onNavigateUp = {},
        onAddCanteenClick = {},
        canteenResult = FlowState.Loading
    )
}