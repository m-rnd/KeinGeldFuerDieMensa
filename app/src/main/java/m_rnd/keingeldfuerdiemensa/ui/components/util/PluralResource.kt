package m_rnd.keingeldfuerdiemensa.ui.components.util

import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun pluralResource(
    @PluralsRes id: Int,
    quantity: Int,
    vararg formatArgs: Any? = emptyArray()
): String {
    return LocalContext.current.resources
        .getQuantityString(id, quantity, *formatArgs)
}