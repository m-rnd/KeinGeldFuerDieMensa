package m_rnd.keingeldfuerdiemensa.entities.util


sealed class NavigationTarget(open val name: String) {

    object Up : NavigationTarget("up")
    object Home : NavigationTarget("home")
    object AddCanteen : NavigationTarget("add_canteen")

    sealed class Settings(override val name: String) : NavigationTarget(name) {
        object Canteen : Settings("canteen_settings")
    }
}