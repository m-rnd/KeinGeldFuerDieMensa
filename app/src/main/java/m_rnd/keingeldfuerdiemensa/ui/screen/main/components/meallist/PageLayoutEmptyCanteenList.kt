package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.meallist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R

@Composable
fun PageLayoutEmptyCanteenList(
    onAddCanteenClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(R.string.main_add_canteen_text)
        )
        Button(onClick = onAddCanteenClick) {
            Text(stringResource(R.string.main_add_canteen_button))
        }
    }
}


@Composable
@Preview
fun AddMensaLayoutPreview() {
    PageLayoutEmptyCanteenList {}
}