package m_rnd.keingeldfuerdiemensa.entities.util

sealed class DialogResult {
    object Negative : DialogResult()

    sealed class Positive<T>(val value: T) : DialogResult() {
        class CanteenName(newName: String) : Positive<String>(newName)
    }
}