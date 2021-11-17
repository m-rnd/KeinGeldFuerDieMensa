package m_rnd.keingeldfuerdiemensa.ui.screen.settings.components.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.util.AppResult
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.components.SettingsSectionHeader

@Composable
fun SettingsSectionCanteen(
    onAddCanteenClick: () -> Unit,
    canteenResult: AppResult<List<Canteen>>
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SettingsSectionHeader(text = stringResource(id = R.string.settings_section_header_canteens))
            Icon(
                modifier = Modifier.clickable(onClick = onAddCanteenClick),
                imageVector = Icons.Default.Add,
                contentDescription = "add"
            )
        }
        when (canteenResult) {
            is AppResult.Success -> {
                LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
                    canteenResult.data.forEach { mensa ->
                        item {
                            Text(modifier = Modifier.fillMaxWidth(), text = mensa.name)
                        }
                    }
                })
            }
            is AppResult.Error -> {
                Text(text = canteenResult.reason.additionalInfo)
            }
            is AppResult.Loading -> {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsSectionCanteenPreview() {
    SettingsSectionCanteen(onAddCanteenClick = {}, canteenResult = AppResult.Loading)
}