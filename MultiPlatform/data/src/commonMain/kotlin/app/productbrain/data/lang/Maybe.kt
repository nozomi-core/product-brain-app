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


    companion object {
        suspend inline fun <T> tryResult(crossinline block: suspend () -> T): Maybe<T> {
            return try {
                Value(block())
            } catch (e: Exception) {
                Error(e)
            }
        }

        inline fun <T> tryResultBlocking(block: () -> T): Maybe<T> {
            return try {
                Value(block())
            } catch (e: Exception) {
                Error(e)
            }
        }
    }
}