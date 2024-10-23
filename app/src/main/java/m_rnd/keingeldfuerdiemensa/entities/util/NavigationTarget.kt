package m_rnd.keingeldfuerdiemensa.entities.util


sealed class NavigationTarget(open val name: String) {

    data object Up : NavigationTarget("up")
    data object Home : NavigationTarget("home")
    data object AddCanteen : NavigationTarget("add_canteen")
    data object About : NavigationTarget("about")
    data object Settings : NavigationTarget("settings")
}