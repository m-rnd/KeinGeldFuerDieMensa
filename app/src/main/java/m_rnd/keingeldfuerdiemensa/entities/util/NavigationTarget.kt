package m_rnd.keingeldfuerdiemensa.entities.util


sealed class NavigationTarget {

    object Up : NavigationTarget()
    object Home : NavigationTarget()
    object AddCanteen : NavigationTarget()

    sealed class Settings : NavigationTarget() {
        object Canteen : Settings()
    }
}

inline fun <reified Target : NavigationTarget> getTargetName(): String {
    return Target::class.java.simpleName
}

fun NavigationTarget.getTargetName(): String {
    return this::class.java.simpleName
}