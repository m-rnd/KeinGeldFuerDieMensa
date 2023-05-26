package m_rnd.keingeldfuerdiemensa.ui.screen.addcanteen.namedialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.CanteenSearchResult
import m_rnd.keingeldfuerdiemensa.entities.util.DialogResult
import m_rnd.keingeldfuerdiemensa.ui.theme.DialogElevation

private val cornerRadius = 16.dp

@Composable
fun CanteenNameDialog(
    canteen: CanteenSearchResult,
    onDialogResult: (DialogResult) -> Unit
) {
    var canteenName = remember { mutableStateOf(TextFieldValue(canteen.name)) }
    Dialog({ onDialogResult(DialogResult.Negative) }) {
        Content(
            canteenName = canteenName,
            onSaveClick = { onDialogResult(DialogResult.Positive.CanteenName(canteenName.value.text)) }
        )
    }
}

@Composable
private fun Content(
    canteenName: MutableState<TextFieldValue>,
    onSaveClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = DialogElevation,
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(cornerRadius)
        ) {
            Text(
                text = stringResource(R.string.dialog_canteen_name_header),
                style = MaterialTheme.typography.titleLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = canteenName.value,
                onValueChange = { canteenName.value = it },
                placeholder = {
                    Text(
                        stringResource(R.string.dialog_canteen_name_text_field_placeholder)
                    )
                })
            TextButton(
                modifier = Modifier.align(End),
                onClick = onSaveClick
            ) {
                Text(stringResource(R.string.common_save))
            }
        }
    }
}

@Preview
@Composable
private fun CanteenNameDialogPreview() {
    Content(remember { mutableStateOf(TextFieldValue("Mensa am Park")) }, {})
}