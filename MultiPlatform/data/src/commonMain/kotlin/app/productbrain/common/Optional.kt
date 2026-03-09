package app.productbrain.common

sealed interface Optional<out T> {
    class Value<T>(val value: T): Optional<T>
    data object None: Optional<Nothing>
}

