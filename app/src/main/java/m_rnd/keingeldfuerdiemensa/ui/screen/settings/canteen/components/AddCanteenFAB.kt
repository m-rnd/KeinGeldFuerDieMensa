package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import m_rnd.keingeldfuerdiemensa.R

@Composable
fun AddCanteenFAB(
    isVisible: Boolean,
    onFABClick: () -> Unit
) {
    val scaleAndAlpha = animateFloatAsState(if (isVisible) 1f else 0f).value
    FloatingActionButton(
        modifier = Modifier
            .scale(scaleAndAlpha)
            .alpha(scaleAndAlpha),
        backgroundColor = MaterialTheme.colors.primary,
        onClick = onFABClick
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.canteen_settings_content_description_add_canteen)
        )
    }
}