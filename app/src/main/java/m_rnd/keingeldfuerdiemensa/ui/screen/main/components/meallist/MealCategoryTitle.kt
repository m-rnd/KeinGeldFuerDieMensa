package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.meallist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MealCategoryTitle(modifier: Modifier = Modifier, categoryName: String) {
    Text(
        modifier = modifier.padding(top = 16.dp),
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.onSurface,
        text = categoryName)
}