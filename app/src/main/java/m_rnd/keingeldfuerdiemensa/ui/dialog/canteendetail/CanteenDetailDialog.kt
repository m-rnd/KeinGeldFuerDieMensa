package m_rnd.keingeldfuerdiemensa.ui.dialog.canteendetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.outlinedButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.presentation.dialog.CanteenDetailDialogViewModel

private val cornerRadius = 16.dp

@Composable
fun CanteenDetailDialog(viewModel: CanteenDetailDialogViewModel) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(cornerRadius)
        ) {
            Text(text = "Mensa-Informationen", style = MaterialTheme.typography.h6)
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = TextFieldValue(""), onValueChange = {}, placeholder = { Text(
                "Mensa-Name"
            )})
            OutlinedButton(
                onClick = { },
                colors = outlinedButtonColors(contentColor = MaterialTheme.colors.error)
            ) {
                Text("Mensa Löschen")
            }
            TextButton(
                modifier = Modifier.align(End),
                onClick = { /*TODO*/ }
            ) {
                Text("Speichern")
            }
        }
    }
}