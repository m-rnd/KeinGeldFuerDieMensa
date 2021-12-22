package m_rnd.keingeldfuerdiemensa.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import m_rnd.keingeldfuerdiemensa.R

private val OpenSans = FontFamily(
    Font(R.font.open_sans_regular, weight = FontWeight.Normal),
    Font(R.font.open_sans_semibold, weight = FontWeight.Medium)
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = OpenSans
)