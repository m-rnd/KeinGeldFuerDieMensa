package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import m_rnd.keingeldfuerdiemensa.ui.components.util.coloroverrides.translucentBackgroundColor
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@Composable
fun MealPlanCanteenTitle(modifier: Modifier = Modifier, canteenName: String) {
    Box(
        modifier = Modifier.statusBarsPadding()
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .background(translucentBackgroundColor())
                .padding(bottom = 16.dp),
            style = Typography.h5,
            color = MaterialTheme.colors.primary,
            text = canteenName,
            textAlign = TextAlign.Center
        )
    }
}