package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissibleCanteenListItem(
    canteen: Canteen,
    onCanteenDelete: (Canteen) -> Unit,
    onCanteenVisibilityChange: (Canteen) -> Unit
) {
    val state = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                onCanteenDelete(canteen)
            }
            it == DismissValue.DismissedToStart
        }
    )
    SwipeToDismiss(
        modifier = Modifier.padding(vertical = 1.dp),
        state = state,
        directions = setOf(DismissDirection.EndToStart),
        background = {
            val backgroundColor by animateColorAsState(
                when (state.targetValue) {
                    DismissValue.Default -> MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    DismissValue.DismissedToEnd -> MaterialTheme.colorScheme.error
                    DismissValue.DismissedToStart -> MaterialTheme.colorScheme.error
                }
            )
            val iconColor by animateColorAsState(
                when (state.targetValue) {
                    DismissValue.Default -> contentColorFor(
                        MaterialTheme.colorScheme.primary.copy(
                            alpha = 0.1f
                        )
                    )

                    DismissValue.DismissedToEnd -> contentColorFor(MaterialTheme.colorScheme.error)
                    DismissValue.DismissedToStart -> contentColorFor(MaterialTheme.colorScheme.error)
                }
            )

            val scale by animateFloatAsState(
                if (state.targetValue == DismissValue.Default) 0.75f else 1f
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    painterResource(R.drawable.ic_delete),
                    contentDescription = stringResource(R.string.canteen_settings_content_description_delete_canteen),
                    tint = iconColor,
                    modifier = Modifier.scale(scale)
                )
            }
        },
        dismissContent = {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(if (state.dismissDirection != null) 1f else 0f)
                    .selectable(
                        selected = state.dismissDirection != null,
                        enabled = true,
                        role = null,
                        onClick = {}),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = animateDpAsState(
                        if (state.dismissDirection != null) 4.dp else 0.dp
                    ).value
                ),
                shape = if (state.dismissDirection != null) RoundedCornerShape(4.dp) else RectangleShape
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
                        R.drawable.ic_visibility
                    } else {
                        R.drawable.ic_visibility_off
                    }

                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                        IconButton(onClick = {
                            onCanteenVisibilityChange(
                                canteen
                            )
                        }) {
                            Icon(
                                painter = painterResource(icon),
                                contentDescription = stringResource(R.string.canteen_settings_content_description_toggle_visibility),
                            )
                        }
                    }
                }
            }
        })
}
