package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.ui.components.banner.InfoBanner
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme

@Composable
fun EmptyMealPlan(
    onAddCanteenClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        InfoBanner(
            contentText = stringResource(R.string.main_add_canteen_text),
            buttonText = stringResource(R.string.main_add_canteen_button),
            onButtonClick = onAddCanteenClick
        )
    }
}


@Composable
@Preview
fun EmptyMealPlanPreview() {
    AppTheme {
        EmptyMealPlan {}
    }
}