package app.productbrain.common

sealed class Maybe<out T> {
    class Value<T>(val value: T): Maybe<T>()
    class Error(val exception: Exception?): Maybe<Nothing>()

    suspend fun onSuccess(callback: suspend (T) -> Unit): Maybe<T> {
        if(this is Value) {
            callback(value)
        }
        return this
    }

    fun getOrNull(): T? {
        return if(this is Value) {
            value
        } else {
            null
        }
    }

    suspend fun <R> then(block: suspend (T) -> R): Maybe<R> {
        return when (this) {
            is Value -> {
                try {
                    Value(block(value))
                } catch (e: Exception) {
                    Forest.e(e)
                    Error(e)
                }
            }
            is Error -> this
        }
    }

    fun toOptional(): Optional<T> {
        return if(this is Value) {
            Optional.Value(value)
        } else {
            Optional.None
        }
    }

    companion object {
        suspend fun <T> tryMaybe(block: suspend () -> T): Maybe<T> {
            return try {
                Value(block())
            } catch (e: Exception) {
                e.printStackTrace() //TODO: Need this?? Use Timber
                Error(e)
            }
        }

        fun <T> tryMaybeBlocking(block: () -> T): Maybe<T> {
            return try {
                Value(block())
            } catch (e: Exception) {
                Error(e)
            }
        }

        fun <T> of(value: T?): Maybe<T> {
            return if(value == null) {
                Error(null)
            } else {
                Value(value)
            }
        }
    }
}

suspend fun <T> tryMaybe(block: suspend () -> T) = Maybe.tryMaybe(block)

//Used when chaining a Maybe then function that also returns a Maybe, it needs to be flattened
suspend fun <T, R> Maybe<Maybe<T>>.unwrap(block: suspend (T) -> R): Maybe<R> {
    return when (this) {
        is Maybe.Value -> when (val inner = value) {
            is Maybe.Value -> try {
                Maybe.Value(block(inner.value))
            } catch (e: Exception) {
                e.printStackTrace() //TODO: Need this?? Use Timber
                Maybe.Error(e)
            }
            is Maybe.Error -> inner
        }
        is Maybe.Error -> this
    }
}