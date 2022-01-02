package m_rnd.keingeldfuerdiemensa.ui.components.banner

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme

@Composable
fun InfoBanner(
    modifier: Modifier = Modifier,
    contentText: String,
    buttonText: String? = null,
    onButtonClick: () -> Unit = { }
) {
    Banner(
        modifier = modifier,
        color = MaterialTheme.colors.primary,
        titleText = stringResource(R.string.common_info),
        contentText = contentText,
        buttonText = buttonText,
        onButtonClick = onButtonClick

    )
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
fun InfoBannerPreview() {
    AppTheme {
        InfoBanner(
            Modifier,
            contentText = "some info message",
            buttonText = "Fix",
            onButtonClick = {}
        )
    }
}