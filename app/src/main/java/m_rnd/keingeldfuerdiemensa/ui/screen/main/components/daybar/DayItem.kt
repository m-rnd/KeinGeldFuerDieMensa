package m_rnd.keingeldfuerdiemensa.ui.screen.main.components.daybar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.common.extensions.toVisualDate
import m_rnd.keingeldfuerdiemensa.common.extensions.toVisualDay

@Composable
fun DayItem(timestamp: Long) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(style = MaterialTheme.typography.subtitle1, text = timestamp.toVisualDay())
        Text(style = MaterialTheme.typography.caption, text = timestamp.toVisualDate())
    }
}