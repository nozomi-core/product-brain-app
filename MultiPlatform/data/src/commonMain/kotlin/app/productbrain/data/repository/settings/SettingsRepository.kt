package app.productbrain.data.repository.settings

import app.productbrain.data.common.CountryCode

class SettingsRepository(
    private val dao: SettingsDao
) {

    @Suppress("UNCHECKED_CAST")
    suspend fun <T> get(key: SettingKey<T>): T {
        val entity = dao.getRaw(key.key) ?: return key.defaultValue
        val stored = entity.value

        val value: Any = when (key.defaultValue) {
            is String  -> stored
            is Int     -> stored.toInt()
            is Boolean -> stored.toBooleanStrict()
            is Float   -> stored.toFloat()
            is Long    -> stored.toLong()
            is CountryCode -> CountryCode.findByCode(entity.value) ?: key.defaultValue
            else -> throw IllegalStateException("Unsupported type")
        }

        return value as T
    }

    suspend fun <T> set(key: SettingKey<T>, value: T) {
        val value: String = when (value) {
            is String,
            is Int,
            is Boolean,
            is Float,
            is Long  -> value.toString()
            is CountryCode -> value.code
            else -> throw IllegalStateException("Unsupported type")
        }

        dao.setRaw(SettingEntity(key.key, value))
    }
}