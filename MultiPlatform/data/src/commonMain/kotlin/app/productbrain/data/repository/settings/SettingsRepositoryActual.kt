package app.productbrain.data.repository.settings

import app.productbrain.data.ClockInstant
import app.productbrain.data.common.CountryCode
import app.productbrain.data.common.CurrencyCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SettingsRepository {
    suspend fun <T : Any> get(key: SettingKey<T>): T
    suspend fun <T : Any> set(key: SettingKey<T>, value: T)
    fun requireSettingKeySet(requireSettings: List<SettingKey<*>>): Flow<Boolean>
}

class SettingsRepositoryActual(
    private val dao: SettingsDao
): SettingsRepository {
    @Suppress("UNCHECKED_CAST")
    override suspend fun <T : Any> get(key: SettingKey<T>): T {
        val entity = dao.getRaw(key.key) ?: return key.defaultValue
        val stored = entity.value

        val value: Any = when (key.type) {
            String::class  -> stored
            Int::class     -> stored.toInt()
            Boolean::class -> stored.toBooleanStrict()
            Float::class   -> stored.toFloat()
            Long::class    -> stored.toLong()
            CountryCode::class -> CountryCode.findByCode(entity.value) ?: key.defaultValue
            CurrencyCode::class -> CurrencyCode.findByCode(entity.value) ?: key.defaultValue
            ClockInstant::class -> ClockInstant(stored.toLong())
            else -> throw IllegalStateException("Unsupported type")
        }

        return value as T
    }

    override suspend fun <T : Any> set(key: SettingKey<T>, value: T) {
        val value: String = when (value) {
            is String,
            is Int,
            is Boolean,
            is Float,
            is Long  -> value.toString()
            is CountryCode -> value.code
            is CurrencyCode -> value.code
            is ClockInstant -> value.utcMillis.toString()
            else -> throw IllegalStateException("Unsupported type")
        }

        dao.setRaw(SettingEntity(key.key, value))
    }

    override fun requireSettingKeySet(requireSettings: List<SettingKey<*>>): Flow<Boolean> {
        return dao.getAllSettingsFlow().map { settingList ->
            val allSetKeys = settingList.map { it.key }

            requireSettings.all { item ->
                item.key in allSetKeys
            }
        }
    }
}