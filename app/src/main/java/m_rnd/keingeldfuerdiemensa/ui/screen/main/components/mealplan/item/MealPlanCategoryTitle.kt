package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MealPlanCategoryTitle(modifier: Modifier = Modifier, categoryName: String) {
    Text(
        modifier = modifier.padding(top = 16.dp),
        style = MaterialTheme.typography.labelLarge,
        text = categoryName
    )
}