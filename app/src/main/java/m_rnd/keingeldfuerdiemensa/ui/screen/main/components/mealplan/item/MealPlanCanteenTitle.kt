package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import m_rnd.keingeldfuerdiemensa.ui.components.util.coloroverrides.translucentBackgroundColor
import m_rnd.keingeldfuerdiemensa.ui.theme.AppBarHeight

@Composable
fun MealPlanCanteenTitle(modifier: Modifier = Modifier, canteenName: String) {
    Box(
        modifier = modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .height(AppBarHeight)
            .background(translucentBackgroundColor()),
        contentAlignment = Alignment.Center
    ) {
        Text(
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            text = canteenName
        )
    }
}