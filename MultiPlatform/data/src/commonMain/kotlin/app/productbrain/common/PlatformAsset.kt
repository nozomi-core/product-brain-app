package app.productbrain.common

import kotlinx.serialization.KSerializer

interface PlatformAssets {
    suspend fun  <T> getJson(filename: String, serializer: KSerializer<T>): Maybe<T>
}