package m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.ui.theme.BottomBarElevation
import m_rnd.keingeldfuerdiemensa.ui.theme.TranslucentSurfaceAlpha

@Composable
fun BottomSearchBar(
    onNavigateUp: () -> Unit,
    onCanteenInputChanged: (TextFieldValue) -> Unit,
    searchEnabled: Boolean,
    canteenSearchInput: TextFieldValue
) {
    Surface(
        tonalElevation = BottomBarElevation,
        modifier = Modifier.alpha(TranslucentSurfaceAlpha),
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .imePadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onPrimaryContainer) {
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
}