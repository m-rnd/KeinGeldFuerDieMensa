package m_rnd.keingeldfuerdiemensa.ui.components.banner

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason
import m_rnd.keingeldfuerdiemensa.ui.components.util.getLocalizedReason

@Composable
fun ErrorBanner(
    modifier: Modifier = Modifier,
    errorReason: ErrorReason,
    buttonText: String? = null,
    onButtonClick: () -> Unit = { }
) {
    Banner(
        modifier = modifier,
        color = MaterialTheme.colorScheme.error,
        titleText = stringResource(R.string.common_error),
        contentText = errorReason.getLocalizedReason(),
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
fun ErrorBannerPreview() {
    ErrorBanner(
        Modifier,
        errorReason = ErrorReason.Unknown,
        buttonText = "Fix",
        onButtonClick = {}
    )
}