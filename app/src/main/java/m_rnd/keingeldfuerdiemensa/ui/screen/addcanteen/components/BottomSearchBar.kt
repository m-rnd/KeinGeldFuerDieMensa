package m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.ui.components.util.translucentSurfaceColor
import m_rnd.keingeldfuerdiemensa.ui.theme.BottomBarElevation

@Composable
fun BottomSearchBar(
    onNavigateUp: () -> Unit,
    onCanteenInputChanged: (TextFieldValue) -> Unit,
    searchEnabled: Boolean,
    canteenSearchInput: TextFieldValue
) {
    Surface(
        elevation = BottomBarElevation,
        color = translucentSurfaceColor(BottomBarElevation)
    ) {
        Row(
            modifier = Modifier.navigationBarsWithImePadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateUp) {
                Icon(
                    Icons.Outlined.Close,
                    stringResource(id = R.string.common_content_description_navigate_up)
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, top = 16.dp, bottom = 16.dp),
                maxLines = 1,
                singleLine = true,
                enabled = searchEnabled,
                value = canteenSearchInput,
                placeholder = { Text(stringResource(R.string.add_canteen_search_text_field_placeholder)) },
                onValueChange = { onCanteenInputChanged(it) },
            )
        }
    }
}