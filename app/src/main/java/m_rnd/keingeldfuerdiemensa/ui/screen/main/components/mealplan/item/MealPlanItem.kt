package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan.item

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@Composable
fun MealPlanItem(
    modifier: Modifier = Modifier,
    mealTitles: List<String>,
    mealDescription: String?,
    mealPrice: Float,
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                mealTitles.forEach {
                    Text(
                        style = Typography.titleMedium,
                        text = it
                    )
                }
            }

            Text(
                modifier = Modifier.align(CenterVertically),
                textAlign = TextAlign.End,
                style = Typography.labelLarge,
                text = "%.2f".format(mealPrice) + "€"
            )
        }
        mealDescription?.let {
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    style = Typography.bodyMedium,
                    text = mealDescription
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
@Preview(showBackground = true)
fun MealPlanItemPreview() {
    AppTheme {
        Column {
            MealPlanItem(
                mealTitles = listOf("Cevapcici"),
                mealDescription = "Zusatzstoffe",
                mealPrice = 1.3f
            )


            MealPlanItem(
                mealTitles = listOf("Cevapcici", "Currywurst", "Spaghetti"),
                mealDescription = "Zusatzstoffe",
                mealPrice = 1.3f
            )
        }
    }
}