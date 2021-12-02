package m_rnd.keingeldfuerdiemensa.ui.components.banner

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.ui.theme.ComposeTestTheme
import m_rnd.keingeldfuerdiemensa.ui.theme.CustomCornerRadius
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography

@Composable
fun Banner(
    modifier: Modifier,
    color: Color,
    titleText: String,
    contentText: String,
    buttonText: String? = null,
    onButtonClick: () -> Unit = { }
) {
    Card(
        modifier
            .fillMaxWidth()
            .padding(CustomCornerRadius),
        shape = RoundedCornerShape(CustomCornerRadius),
        backgroundColor = color.copy(alpha = 0.1f),
        contentColor = color,
        elevation = 0.dp,
        border = BorderStroke(1.dp, color)
    ) {
        Column(
            modifier = Modifier
                .padding(CustomCornerRadius)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(modifier = Modifier, style = Typography.caption, text = titleText.uppercase())
            Text(contentText)

            buttonText?.let {
                TextButton(
                    modifier = Modifier.align(End),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = color
                    ),
                    onClick = onButtonClick) {
                    Text(it)
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun BannerPreview() {
    ComposeTestTheme {
        Column {
            Banner(
                Modifier,
                MaterialTheme.colors.error,
                titleText = "Error",
                contentText = "some error message",
                buttonText = "Fix",
                onButtonClick = {}
            )
            Banner(
                Modifier,
                MaterialTheme.colors.primary,
                titleText = "Error",
                contentText = "some error message",
            )
        }
    }
}