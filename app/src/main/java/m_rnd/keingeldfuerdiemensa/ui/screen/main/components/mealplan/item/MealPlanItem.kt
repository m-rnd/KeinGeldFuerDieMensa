package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
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
    mealDescription: String,
    mealPrice: Float,
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                mealTitles.forEach {
                    Text(
                        style = Typography.h6,
                        text = it
                    )
                }
            }

            Text(
                modifier = Modifier.align(CenterVertically),
                textAlign = TextAlign.End,
                style = Typography.caption,
                text = "%.2f".format(mealPrice) + "€"
            )
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                style = Typography.body2,
                text = mealDescription
            )
        }
    }
}

@ExperimentalAnimationApi
@Composable
@Preview(showBackground = true)
fun MealPlanItemPreview() {
    AppTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
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