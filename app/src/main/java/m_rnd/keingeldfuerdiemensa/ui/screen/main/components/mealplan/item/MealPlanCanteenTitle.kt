package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.ui.components.util.coloroverrides.translucentBackgroundColor

@Composable
fun MealPlanCanteenTitle(modifier: Modifier = Modifier, canteenName: String) {
    Box(
        modifier = Modifier.statusBarsPadding()
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .background(translucentBackgroundColor())
                .padding(vertical = 16.dp),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            text = canteenName,
            textAlign = TextAlign.Center
        )
    }
}