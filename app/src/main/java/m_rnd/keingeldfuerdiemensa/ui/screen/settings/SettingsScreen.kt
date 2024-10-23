package m_rnd.keingeldfuerdiemensa.ui.screen.settings


import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.Canteen
import m_rnd.keingeldfuerdiemensa.entities.ThemeMode
import m_rnd.keingeldfuerdiemensa.entities.mock.PreviewEntity
import m_rnd.keingeldfuerdiemensa.presentation.settings.CanteenList
import m_rnd.keingeldfuerdiemensa.presentation.settings.SettingsViewModel
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.NavigationBarType
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.SystemUiBottomSheetScaffold
import m_rnd.keingeldfuerdiemensa.ui.components.systemui.SystemUiScaffold
import m_rnd.keingeldfuerdiemensa.ui.components.util.pxToDp
import m_rnd.keingeldfuerdiemensa.ui.screen.main.SheetDragHandleHeight
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.BottomSheetDragHandle
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.bottombar.DayBottomBarTabPadding
import m_rnd.keingeldfuerdiemensa.ui.screen.main.components.mealplan.MealPlanTopPadding
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.canteen.canteenSettingItems
import m_rnd.keingeldfuerdiemensa.ui.screen.settings.theme.themeSettingItems
import m_rnd.keingeldfuerdiemensa.ui.theme.AppTheme
import m_rnd.keingeldfuerdiemensa.ui.theme.CustomCornerRadius
import m_rnd.keingeldfuerdiemensa.ui.theme.MainScreenBottomBarHeight

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
) {
    SettingsScreenContent(
        onNavigateUp = viewModel::navigateUp,
        onAddCanteenClick = viewModel::navigateToAddCanteenScreen,
        onCanteenDelete = { viewModel.deleteCanteen(it) },
        onCanteenVisibilityChange = { viewModel.toggleCanteenVisibility(it) },
        canteenResult = remember { viewModel.canteensFlow }.collectAsState().value,
        isSortMode = remember { viewModel.isSortEnabled }.collectAsState().value,
        onSortIconClick = viewModel::toggleSortMode,
        onInfoIconClick = viewModel::navigateToAbout,
        moveCanteen = { from, to -> viewModel.moveCanteen(from, to) },
        themeMode = remember { viewModel.themeMode }.collectAsState().value,
        useDynamicColors = remember { viewModel.useDynamicColors }.collectAsState().value,
        onThemeModeChange = viewModel::onThemeModeChange,
        onDynamicColorSelect = viewModel::onDynamicColorSelect,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenContent(
    onNavigateUp: () -> Unit,
    onAddCanteenClick: () -> Unit,
    onCanteenDelete: (Canteen) -> Unit,
    onCanteenVisibilityChange: (Canteen) -> Unit,
    onSortIconClick: () -> Unit,
    onInfoIconClick: () -> Unit,
    moveCanteen: (Int, Int) -> Unit,
    isSortMode: Boolean,
    canteenResult: CanteenList,
    themeMode: ThemeMode,
    useDynamicColors: Boolean,
    onThemeModeChange: (ThemeMode) -> Unit,
    onDynamicColorSelect: (Boolean) -> Unit,
) {
    val systemBarBottomHeight = WindowInsets.systemBars.getBottom(LocalDensity.current)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
    )

    var hideBottomSheet by remember { mutableStateOf(false) }
    LaunchedEffect(hideBottomSheet) {
        if (hideBottomSheet) {
            scaffoldState.bottomSheetState.partialExpand()
            hideBottomSheet = false
        }
    }

    LaunchedEffect(scaffoldState.bottomSheetState) {
        snapshotFlow { scaffoldState.bottomSheetState.currentValue }.collect { currentValue ->
            if (currentValue != SheetValue.Expanded) {
                onNavigateUp()
            }
        }
    }

    BackHandler {
        hideBottomSheet = true
    }

    SystemUiBottomSheetScaffold(
        sheetContent = { animationProgressProvider ->
            Column(
                modifier = Modifier
                    .graphicsLayer {
                        alpha = animationProgressProvider()
                    }
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomSheetDragHandle()
                DayBottomBarPlaceholder()
            }

            SystemUiScaffold(
                modifier = Modifier.graphicsLayer {
                    alpha = 1 - animationProgressProvider()
                },
                navigationBarType = NavigationBarType.BACKGROUND_TRANSLUCENT,
                topBar = {
                    SettingsToolbar(
                        title = stringResource(R.string.settings_title),
                        onNavigationIconClick = { hideBottomSheet = true },
                        onInfoIconClick = onInfoIconClick
                    )
                },
            ) { paddingValues ->
                var contentVisible by remember {
                    mutableStateOf(false)
                }
                SideEffect { contentVisible = true }
                AnimatedVisibility(visible = contentVisible, enter = fadeIn()) {
                    SettingsItems(
                        paddingValues = paddingValues,
                        canteenResult = canteenResult,
                        onCanteenDelete = onCanteenDelete,
                        onCanteenVisibilityChange = onCanteenVisibilityChange,
                        onSortIconClick = onSortIconClick,
                        onAddCanteenClick = onAddCanteenClick,
                        moveCanteen = moveCanteen,
                        isSortMode = isSortMode,
                        themeMode = themeMode,
                        useDynamicColors = useDynamicColors,
                        onThemeModeChange = onThemeModeChange,
                        onDynamicColorSelect = onDynamicColorSelect
                    )
                }
            }


        },
        scaffoldState = scaffoldState,
        collapsedSheetCornerRadius = CustomCornerRadius + SheetDragHandleHeight + DayBottomBarTabPadding,
        collapsedSheetHeight = MainScreenBottomBarHeight + systemBarBottomHeight.pxToDp() + SheetDragHandleHeight,
    ) {
        MealListPlaceholder(it)
    }
}

@Composable
private fun SettingsItems(
    paddingValues: PaddingValues,
    canteenResult: CanteenList,
    onCanteenDelete: (Canteen) -> Unit,
    onCanteenVisibilityChange: (Canteen) -> Unit,
    onSortIconClick: () -> Unit,
    onAddCanteenClick: () -> Unit,
    moveCanteen: (Int, Int) -> Unit,
    isSortMode: Boolean,
    themeMode: ThemeMode,
    useDynamicColors: Boolean,
    onThemeModeChange: (ThemeMode) -> Unit,
    onDynamicColorSelect: (Boolean) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .fillMaxHeight(),
        contentPadding = paddingValues
    ) {
        canteenSettingItems(
            canteenResult = canteenResult,
            onCanteenDelete = onCanteenDelete,
            onCanteenVisibilityChange = onCanteenVisibilityChange,
            onSortIconClick = onSortIconClick,
            onAddCanteenClick = onAddCanteenClick,
            moveCanteen = moveCanteen,
            isSortMode = isSortMode
        )
        themeSettingItems(
            selectedThemeMode = themeMode,
            useDynamicColors = useDynamicColors,
            onThemeModeChange = onThemeModeChange,
            onDynamicColorSelect = onDynamicColorSelect
        )
    }
}

@Composable
private fun MealListPlaceholder(paddingValues: PaddingValues) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CanteenTitlePlaceholder()
        MealCategoryPlaceholder()
        MealPlanItemPlaceholder(mealCount = 1)
        MealPlanItemPlaceholder(mealCount = 2)
        MealCategoryPlaceholder()
        MealPlanItemPlaceholder(mealCount = 3)
        MealPlanItemPlaceholder(mealCount = 1)
    }
}

@Composable
private fun CanteenTitlePlaceholder() {
    Surface(
        color = MaterialTheme.colorScheme.primary.copy(0.4f),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(top = MealPlanTopPadding)
            .padding(16.dp)
            .size(width = 164.dp, height = 32.dp)
    ) {}
}

@Composable
private fun MealCategoryPlaceholder() {
    Surface(
        color = MaterialTheme.colorScheme.onBackground.copy(0.4f),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.size(width = 96.dp, height = 20.dp)
    ) {}
}


@Composable
private fun MealPlanItemPlaceholder(
    modifier: Modifier = Modifier,
    mealCount: Int,
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (i in 0..mealCount) {
                    Surface(
                        color = MaterialTheme.colorScheme.onBackground.copy(0.4f),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.size(width = 200.dp, height = 24.dp)
                    ) {}
                }
            }

            Surface(
                color = MaterialTheme.colorScheme.onBackground.copy(0.4f),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.size(width = 48.dp, height = 20.dp)
            ) {}
        }
        Surface(
            color = MaterialTheme.colorScheme.onBackground.copy(0.2f),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(top = 4.dp)
        ) {}
    }
}

@Composable
private fun DayBottomBarPlaceholder() {
    Row(
        Modifier
            .height(MainScreenBottomBarHeight)
            .padding(horizontal = SheetDragHandleHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(CustomCornerRadius),
            modifier = Modifier
                .padding(DayBottomBarTabPadding)
                .fillMaxHeight()
                .width(96.dp)
        ) {}
        for (i in 1..3) {
            Surface(
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.4f),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .padding(DayBottomBarTabPadding)
                    .height(16.dp)
                    .width(96.dp)
            ) {}
        }
    }
}


@Preview
@Composable
fun CanteenSettingsScreenPreview() {
    AppTheme {
        SettingsScreenContent(
            onNavigateUp = {},
            onAddCanteenClick = {},
            canteenResult = CanteenList.Dismissible(listOf(PreviewEntity.CanteenMock())),
            onCanteenDelete = {},
            onCanteenVisibilityChange = {},
            isSortMode = false,
            onSortIconClick = {},
            onInfoIconClick = {},
            moveCanteen = { _, _ -> },
            themeMode = ThemeMode.DARK,
            useDynamicColors = false,
            onThemeModeChange = {},
            onDynamicColorSelect = {},
        )
    }
}