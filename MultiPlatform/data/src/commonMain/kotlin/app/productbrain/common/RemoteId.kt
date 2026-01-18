package app.productbrain.common

sealed class RemoteId<T> {
    data class Bound<T>(val id: T): RemoteId<T>()
    object NoBinding: RemoteId<Nothing>()

    fun toIdString(): String? {
        return when(this) {
            is Bound<*> -> id.toString()
            is NoBinding -> null
        }
    }
}