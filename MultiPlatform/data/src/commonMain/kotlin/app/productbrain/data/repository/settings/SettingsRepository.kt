package app.productbrain.data.repository.settings


class SettingsRepository(private val dao: SettingsDao) {

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
            else -> throw IllegalStateException("Unsupported type")
        }

        return value as T
    }

    suspend fun <T> set(key: SettingKey<T>, value: T) {
        dao.setRaw(SettingEntity(key.key, value.toString()))
    }
}