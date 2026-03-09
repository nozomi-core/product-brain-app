package app.productbrain.common

sealed class RemoteId<out T> {
    data class Bound<T>(val id: T): RemoteId<T>()
    object NoBinding: RemoteId<Nothing>()

    fun toIdString(): String? {
        return when(this) {
            is Bound<*> -> id.toString()
            is NoBinding -> null
        }
    }

    companion object {
        fun <T> fromIdString(id: String?, mapper: (String) -> T): RemoteId<T> {
            if(id == null) return NoBinding
            return Bound(mapper(id))
        }
    }
}