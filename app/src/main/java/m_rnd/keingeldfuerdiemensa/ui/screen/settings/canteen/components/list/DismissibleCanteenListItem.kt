package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DismissibleCanteenListItem(
    canteen: Canteen,
    onCanteenDelete: (Canteen) -> Unit,
    onCanteenVisibilityChange: (Canteen) -> Unit
) {
    val state = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) { onCanteenDelete(canteen) }
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
                    DismissValue.Default -> MaterialTheme.colors.primary.copy(alpha = 0.1f)
                    DismissValue.DismissedToEnd -> MaterialTheme.colors.error
                    DismissValue.DismissedToStart -> MaterialTheme.colors.error
                }
            )
            val iconColor by animateColorAsState(
                when (state.targetValue) {
                    DismissValue.Default -> contentColorFor(MaterialTheme.colors.primary.copy(alpha = 0.1f))
                    DismissValue.DismissedToEnd -> contentColorFor(MaterialTheme.colors.error)
                    DismissValue.DismissedToStart -> contentColorFor(MaterialTheme.colors.error)
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
                    contentDescription = "delete",
                    tint = iconColor,
                    modifier = Modifier.scale(scale)
                )
            }
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(if (state.dismissDirection != null) 1f else 0f),
            elevation = animateDpAsState(
                if (state.dismissDirection != null) 4.dp else 0.dp
            ).value,
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
                    style = MaterialTheme.typography.subtitle1,
                    overflow = TextOverflow.Ellipsis,
                    text = canteen.name
                )

                val icon =
                    if (canteen.isVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                IconButton(onClick = { onCanteenVisibilityChange(canteen) }) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = "visibility",
                        tint = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
                    )
                }

            }
        }
    }
}
