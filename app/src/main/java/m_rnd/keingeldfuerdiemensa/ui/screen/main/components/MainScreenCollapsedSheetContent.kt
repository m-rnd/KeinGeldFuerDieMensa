package m_rnd.keingeldfuerdiemensa.ui.screen.main.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.MealPlan
import m_rnd.keingeldfuerdiemensa.ui.screen.main.SheetDragHandleHeight
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.bottombar.DayBottomBar
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.SettingsToolbar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenCollapsedSheetContent(
    animationProgressProvider: () -> Float,
    mealPlans: List<MealPlan>,
    pagerState: PagerState,
) {
    Column(
        modifier = Modifier
            .zIndex(1f)
            .graphicsLayer {
                alpha = animationProgressProvider()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BottomSheetDragHandle()

        DayBottomBar(
            tabEdgePadding = SheetDragHandleHeight,
            meals = mealPlans,
            pagerState = pagerState
        )
    }

    SettingsContentPlaceholder(
        Modifier
            .graphicsLayer {
                alpha = 1 - animationProgressProvider()
            }
            .fillMaxHeight()
    )
}

@Composable
private fun SettingsContentPlaceholder(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        SettingsToolbar(
            onNavigationIconClick = { },
            onInfoIconClick = { },
            title = stringResource(R.string.settings_title)
        )

        for (i in 1..10) {
            Row {
                Surface(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(0.8f)
                        .fillMaxWidth()
                        .height(24.dp)
                ) {}
                Surface(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(0.2f)
                        .fillMaxWidth()
                        .height(24.dp)
                ) {}
            }
        }
    }
}