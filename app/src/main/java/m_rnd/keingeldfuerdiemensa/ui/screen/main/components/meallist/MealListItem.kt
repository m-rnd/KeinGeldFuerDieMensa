package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.meallist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.ui.theme.OnSurfaceMediumEmphasis
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@Composable
fun MealListItem(
    modifier: Modifier = Modifier,
    mealTitle: String,
    mealDescription: String,
    mealPrice: Float,
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.85f),
                style = Typography.h6,
                text = mealTitle)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterVertically),
                textAlign = TextAlign.End,
                style = Typography.caption,
                text = "%.2f".format(mealPrice) + "€")
        }
        //TODO find out how alpha overlays work
        Text(
            modifier = Modifier.padding(top = 4.dp),
            color = OnSurfaceMediumEmphasis,
            style = Typography.body2,
            text = mealDescription)
    }
}

@ExperimentalAnimationApi
@Composable
@Preview(showBackground = true)
fun MealListItemPreview() {
    MealListItem(
        mealTitle = "Cevapcici",
        mealDescription = "Zusatzstoffe",
        mealPrice = 1.3f
    )
}