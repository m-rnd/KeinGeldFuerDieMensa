package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.meallist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@Composable
fun MealListTitle(modifier: Modifier = Modifier, canteenName: String) {
    Text(
        modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
        style = Typography.h5,
        color = MaterialTheme.colors.primary,
        text = canteenName)
}