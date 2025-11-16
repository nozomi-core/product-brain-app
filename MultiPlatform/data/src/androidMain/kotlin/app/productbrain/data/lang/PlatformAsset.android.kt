package app.productbrain.data.lang

import android.content.Context
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import java.io.InputStream

class AndroidPlatformAssets(private val appContext: Context): PlatformAssets {
    override suspend fun <T> getJson(filename: String, serializer: KSerializer<T>): Maybe<T> {
        return Maybe.tryResult {
            val inputStream: InputStream = appContext.assets.open(filename)
            val text = inputStream.bufferedReader().use { it.readText() }
            Json.decodeFromString(serializer, text)
        }
    }
}