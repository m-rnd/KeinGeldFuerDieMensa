package m_rnd.keingeldfuerdiemensa.ui.screen.settings.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.BuildConfig
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.presentation.settings.AboutViewModel
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.NavigationBarType
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.SystemUiScaffold
import m_rnd.keingeldfuerdiemensa.ui.components.util.UriHandlerText
import m_rnd.keingeldfuerdiemensa.ui.components.util.annotatedStringResource
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme

@Composable
fun AboutScreen(viewModel: AboutViewModel) {
    Content(
        onNavigateUp = viewModel::navigateUp
    )
}

@Composable
private fun Content(
    onNavigateUp: () -> Unit
) {
    SystemUiScaffold(
        topBar = { AboutToolbar(onNavigationIconClick = onNavigateUp) },
        navigationBarType = NavigationBarType.BACKGROUND_TRANSLUCENT
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            UriHandlerText(annotatedStringResource(R.string.about_mensa_api))

            UriHandlerText(
                modifier = Modifier.weight(1f),
                text = annotatedStringResource(R.string.about_github)
            )

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(
                        id = R.string.about_app_info,
                        BuildConfig.VERSION_NAME,
                        BuildConfig.VERSION_CODE
                    )
                )
            }
        }
    }
}


@Preview
@Composable
fun AboutScreenPreview() {
    AppTheme {
        Content {}
    }
}