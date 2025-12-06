package app.productbrain.data.lang

sealed class Maybe<out T> {
    class Value<T>(val value: T): Maybe<T>()
    class Error(val exception: Exception?): Maybe<Nothing>()

    fun onSuccess(callback: (T) -> Unit): Maybe<T> {
        if(this is Value) {
            callback(value)
        }
        return this
    }

    fun getOrDefault(default: @UnsafeVariance T): T {
        return if(this is Value) {
            value
        } else {
            default
        }
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
                    Error(e)
                }
            }
            is Error -> this

        }
    }

     suspend fun <R> pipe(block: suspend (Maybe<T>) -> Maybe<R>): Maybe<R> {
        return block(this)
    }


    companion object {
        suspend fun <T> tryResult(block: suspend () -> T): Maybe<T> {
            return try {
                Value(block())
            } catch (e: Exception) {
                Error(e)
            }
        }

        fun <T> tryResultBlocking(block: () -> T): Maybe<T> {
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