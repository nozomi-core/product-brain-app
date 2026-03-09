package app.productbrain.common

import kotlinx.coroutines.runBlocking

class LazyState<out T>(private val func: suspend () -> T) {
    private var cachedT: T? = null

    val value: T
        get() {
            return if (cachedT != null)
                cachedT as T
            else {
                runBlocking {
                    cachedT = func()
                    cachedT as T
                }
            }
        }
}