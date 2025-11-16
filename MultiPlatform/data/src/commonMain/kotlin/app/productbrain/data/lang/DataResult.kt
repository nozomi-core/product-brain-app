package app.productbrain.data.lang

sealed class DataResult<out T> {
    class Success<T>(val value: T): DataResult<T>()
    class Error(val exception: Exception?): DataResult<Nothing>()

    fun onSuccess(callback: (T) -> Unit): DataResult<T> {
        if(this is Success) {
            callback(value)
        }
        return this
    }


    companion object {
        suspend inline fun <T> tryResult(crossinline block: suspend () -> T): DataResult<T> {
            return try {
                Success(block())
            } catch (e: Exception) {
                Error(e)
            }
        }
    }
}