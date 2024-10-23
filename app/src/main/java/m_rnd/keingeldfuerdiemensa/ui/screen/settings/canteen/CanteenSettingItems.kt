package m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.mock.PreviewEntity
import m_rnd.keingeldfuerdiemensa.presentation.settings.CanteenList
import m_rnd.keingeldfuerdiemensa.ui.components.banner.InfoBanner
import m_rnd.keingeldfuerdiemensa.ui.components.util.modifier.ignoreHorizontalParentPadding
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list.DismissibleCanteenList
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.components.list.ReorderableCanteenList
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme
import m_rnd.keingeldfuerdiemensa.ui.theme.SettingsCanteenListMaxHeight
import m_rnd.keingeldfuerdiemensa.ui.theme.Typography


fun LazyListScope.canteenSettingItems(
    canteenResult: CanteenList,
    onAddCanteenClick: () -> Unit,
    onCanteenDelete: (Canteen) -> Unit,
    onCanteenVisibilityChange: (Canteen) -> Unit,
    onSortIconClick: () -> Unit, moveCanteen: (Int, Int) -> Unit,
    isSortMode: Boolean
) {
    val sortingEnabled = canteenResult !is CanteenList.EmptyList

    item { CanteenListHeaderItem(onSortIconClick, onAddCanteenClick, sortingEnabled, isSortMode) }
    item { CanteenList(canteenResult, moveCanteen, onCanteenDelete, onCanteenVisibilityChange) }
    item { AddCanteenSection(canteenResult, onAddCanteenClick) }
}

@Composable
private fun CanteenList(
    canteenResult: CanteenList,
    moveCanteen: (Int, Int) -> Unit,
    onCanteenDelete: (Canteen) -> Unit,
    onCanteenVisibilityChange: (Canteen) -> Unit
) {
    when (canteenResult) {
        is CanteenList.Reorderable -> {
            ReorderableCanteenList(
                modifier = Modifier
                    .ignoreHorizontalParentPadding(16.dp)
                    .animateContentSize()
                    .heightIn(0.dp, SettingsCanteenListMaxHeight),
                moveCanteen = moveCanteen,
                canteenResult = canteenResult
            )
        }

        is CanteenList.Dismissible -> {
            DismissibleCanteenList(
                modifier = Modifier
                    .ignoreHorizontalParentPadding(16.dp)
                    .animateContentSize()
                    .heightIn(0.dp, SettingsCanteenListMaxHeight),
                onCanteenDelete = onCanteenDelete,
                onCanteenVisibilityChange = onCanteenVisibilityChange,
                canteenResult = canteenResult
            )
        }

        is CanteenList.EmptyList -> {}
    }
}

@Composable
private fun CanteenListHeaderItem(
    onSortIconClick: () -> Unit,
    onAddIconClick: () -> Unit,
    sortingEnabled: Boolean,
    isSortMode: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .ignoreHorizontalParentPadding(16.dp)
            .padding(start = 16.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.weight(1f),
            style = Typography.titleMedium,
            text = stringResource(R.string.canteen_settings_title)
        )
        IconButton(onClick = onAddIconClick) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = stringResource(R.string.canteen_settings_content_description_add_canteen)
            )
        }

        IconButton(onClick = onSortIconClick, enabled = sortingEnabled) {
            Icon(
                imageVector =
                if (isSortMode) Icons.Outlined.Save else Icons.AutoMirrored.Filled.Sort,
                contentDescription = stringResource(R.string.canteen_settings_content_description_toggle_sort_mode)
            )
        }
    }
}

@Composable
private fun AddCanteenSection(
    canteenResult: CanteenList,
    onAddCanteenClick: () -> Unit,
) {
    Box(Modifier.fillMaxWidth()) {
        when (canteenResult) {
            is CanteenList.EmptyList -> InfoBanner(
                modifier = Modifier.ignoreHorizontalParentPadding(16.dp),
                contentText = stringResource(R.string.canteen_settings_info_banner_no_canteens),
                buttonText = null,
                onButtonClick = onAddCanteenClick
            )

            else -> InfoBanner(
                modifier = Modifier.ignoreHorizontalParentPadding(16.dp),
                contentText = stringResource(R.string.canteen_settings_info_banner),
                onButtonClick = onAddCanteenClick
            )
        }
    }
}


@Preview
@Composable
fun EmptyCanteenSettingItemsPreview() {
    AppTheme {
        LazyColumn(Modifier.padding(horizontal = 16.dp)) {
            canteenSettingItems(
                canteenResult = CanteenList.EmptyList,
                onAddCanteenClick = {},
                onCanteenDelete = {},
                onCanteenVisibilityChange = {},
                onSortIconClick = {},
                moveCanteen = { _: Int, _: Int -> },
                isSortMode = false
            )
        }
    }
}

@Preview
@Composable
fun DismissibleCanteenSettingItemsPreview() {
    AppTheme {
        LazyColumn(Modifier.padding(horizontal = 16.dp)) {
            canteenSettingItems(
                canteenResult = CanteenList.Dismissible(
                    listOf(
                        PreviewEntity.CanteenMock(),
                        PreviewEntity.CanteenMock(id = 1)
                    )
                ),
                onAddCanteenClick = {},
                onCanteenDelete = {},
                onCanteenVisibilityChange = {},
                onSortIconClick = {},
                moveCanteen = { _: Int, _: Int -> },
                isSortMode = false
            )
        }
    }
}


@Preview
@Composable
fun ReorderableCanteenSettingItemsPreview() {
    AppTheme {
        LazyColumn(Modifier.padding(horizontal = 16.dp)) {
            canteenSettingItems(
                canteenResult = CanteenList.Reorderable(
                    listOf(
                        PreviewEntity.CanteenMock(),
                        PreviewEntity.CanteenMock(id = 1)
                    ).toMutableStateList()
                ),
                onAddCanteenClick = {},
                onCanteenDelete = {},
                onCanteenVisibilityChange = {},
                onSortIconClick = {},
                moveCanteen = { i: Int, i1: Int -> },
                isSortMode = false
            )
        }
    }
}
