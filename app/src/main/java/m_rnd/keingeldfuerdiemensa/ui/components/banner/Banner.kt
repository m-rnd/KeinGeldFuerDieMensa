package m_rnd.keingeldfuerdiemensa.ui.components.banner

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme
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
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .padding(CustomCornerRadius),
        shape = RoundedCornerShape(CustomCornerRadius),
        colors = CardDefaults.cardColors(
            contentColor = color,
            containerColor = color.copy(alpha = 0.1f)
        ),
        border = BorderStroke(1.dp, color)
    ) {
        Column(
            modifier = Modifier
                .padding(CustomCornerRadius)
                .fillMaxWidth()
        ) {

            Text(style = Typography.labelMedium, text = titleText.uppercase())
            Text(modifier = Modifier.padding(vertical = 8.dp), text = contentText)

            AnimatedVisibility(
                modifier = Modifier.align(End),
                visible = buttonText != null
            ) {
                TextButton(
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = color
                    ),
                    onClick = onButtonClick
                ) {
                    buttonText?.let {
                        Text(buttonText)
                    }
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
    AppTheme {
        Column {
            Banner(
                Modifier,
                MaterialTheme.colorScheme.error,
                titleText = "Error",
                contentText = "some error message",
                buttonText = "Fix",
                onButtonClick = {}
            )
            Banner(
                Modifier,
                MaterialTheme.colorScheme.primary,
                titleText = "Info",
                contentText = "some info message",
            )
        }
    }
}