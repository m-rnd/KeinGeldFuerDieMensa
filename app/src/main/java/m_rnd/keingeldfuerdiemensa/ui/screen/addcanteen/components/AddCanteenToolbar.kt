package m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@Composable
fun AddCanteenToolbar(
    onNavigateUp: () -> Unit,
    onCanteenInputChanged: (TextFieldValue) -> Unit,
    isLoading: Boolean,
    canteenSearchInput: TextFieldValue,
){
    Surface(
        elevation = 5.dp,
        color = MaterialTheme.colors.background
    ) {
        Column {
            Row(
                modifier = Modifier.padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onNavigateUp) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Toolbar")
                }
                Text(
                    style = Typography.h6,
                    color = MaterialTheme.colors.onBackground,
                    text = stringResource(id = R.string.add_canteen_title)
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                maxLines = 1,
                singleLine = true,
                enabled = isLoading.not(),
                trailingIcon = { Icon(imageVector = Icons.Outlined.Search, "search") },
                value = canteenSearchInput,
                placeholder = { Text(stringResource(R.string.add_canteen_search_text_field_placeholder)) },
                onValueChange = { onCanteenInputChanged(it) }
            )
        }
    }
}