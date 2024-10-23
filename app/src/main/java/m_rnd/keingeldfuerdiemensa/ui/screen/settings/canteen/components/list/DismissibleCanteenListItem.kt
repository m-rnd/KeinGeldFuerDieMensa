package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissibleCanteenListItem(
    canteen: Canteen,
    onCanteenDelete: (Canteen) -> Unit,
    onCanteenVisibilityChange: (Canteen) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = rememberSwipeToDismissState(
        confirmValueChange = {
            if (it == SwipeToDismissValue.StartToEnd) {
                onCanteenDelete(canteen)
            }
            it == SwipeToDismissValue.EndToStart
        }
    )
    SwipeToDismissBox(
        modifier = modifier
            .padding(vertical = 1.dp),
        state = state,
        enableDismissFromStartToEnd = true,
        backgroundContent = {
            SwipeToDismissBackground(state)
        },
        content = {
            SwipeToDismissContent(state, canteen, onCanteenVisibilityChange)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SwipeToDismissContent(
    state: SwipeToDismissState,
    canteen: Canteen,
    onCanteenVisibilityChange: (Canteen) -> Unit
) {
    val isDragging = (state.progress == 0f)
    val elevation by animateDpAsState(if (isDragging) 4.dp else 0.dp)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = elevation,
        shape = RoundedCornerShape(elevation)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f),
                style = MaterialTheme.typography.titleSmall,
                overflow = TextOverflow.Ellipsis,
                text = canteen.name
            )

            val icon = if (canteen.isVisible) {
                Icons.Outlined.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }

            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                IconButton(onClick = { onCanteenVisibilityChange(canteen) }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = stringResource(R.string.canteen_settings_content_description_toggle_visibility),
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SwipeToDismissBackground(state: SwipeToDismissState) {
    val backgroundColor by animateColorAsState(
        when (state.targetValue) {
            SwipeToDismissValue.Settled -> MaterialTheme.colorScheme.secondaryContainer
            else -> MaterialTheme.colorScheme.errorContainer
        }
    )
    val iconColor by animateColorAsState(
        when (state.targetValue) {
            SwipeToDismissValue.Settled -> MaterialTheme.colorScheme.secondary
            else -> MaterialTheme.colorScheme.error
        }
    )

    val scale by animateFloatAsState(
        if (state.targetValue == SwipeToDismissValue.Settled) 0.75f else 1f
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.DeleteOutline,
            contentDescription = stringResource(R.string.canteen_settings_content_description_delete_canteen),
            tint = iconColor,
            modifier = Modifier.scale(scale)
        )
    }
}