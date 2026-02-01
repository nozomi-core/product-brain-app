package app.productbrain.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class FieldConverters {

    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return json.encodeToString(value ?: emptyList())
    }

    @TypeConverter
    fun toStringList(value: String?): List<String> {
        return if (value.isNullOrBlank()) {
            emptyList()
        } else {
            json.decodeFromString(value)
        }
    }
}