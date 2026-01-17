package app.productbrain.cache

import androidx.collection.SimpleArrayMap
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class CacheArray<T>(
    val maxSize: Int = 10,
    val keyMapper: (T) -> String,
    val replacePolicy: (List<T>) -> Int
) {
    private val arrayMap = SimpleArrayMap<String, CachedValue<T>>()

    @OptIn(ExperimentalTime::class)
    suspend fun get(key: String, accessor: suspend () -> T): T {
        // 1. Return cached value if present
        arrayMap[key]?.let { cached ->
            return cached.value
        }

        // 2. Load value
        val value = accessor()

        // 3. Evict if necessary
        if (arrayMap.size() >= maxSize) {
            evictOne()
        }

        // 4. Insert into cache
        arrayMap.put(
            key,
            CachedValue(
                value = value,
                createdMillis = Clock.System.now().toEpochMilliseconds()
            )
        )

        return value
    }

    private fun evictOne() {
        if (arrayMap.isEmpty()) return

        // Build list of values for policy decision
        val values = List(arrayMap.size()) { index ->
            arrayMap.valueAt(index).value
        }

        val indexToRemove = replacePolicy(values)
            .coerceIn(0, arrayMap.size() - 1)

        arrayMap.removeAt(indexToRemove)
    }
}

data class CachedValue<T>(
    val value: T,
    val createdMillis: Long
)