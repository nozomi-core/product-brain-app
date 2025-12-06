package app.productbrain.data.model.settings

import app.productbrain.data.ClockInstant
import app.productbrain.data.common.CountryCodeTag
import app.productbrain.data.common.CurrencyCodeTag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SettingsRepository {
    suspend fun <T : Any> get(key: SettingItem<T>): T
    suspend fun <T : Any> set(key: SettingItem<T>, value: T)
    fun requireSettingKeySet(requireSettings: List<SettingItem<*>>): Flow<Boolean>
}

class SettingsRepositoryActual(
    private val dao: SettingsDao
): SettingsRepository {
    @Suppress("UNCHECKED_CAST")
    override suspend fun <T : Any> get(key: SettingItem<T>): T {
        val entity = dao.getRaw(key.key) ?: return key.defaultValue
        val stored = entity.value

        val value: Any = when (key.type) {
            String::class  -> stored
            Int::class     -> stored.toInt()
            Boolean::class -> stored.toBooleanStrict()
            Float::class   -> stored.toFloat()
            Long::class    -> stored.toLong()
            CountryCodeTag::class -> CountryCodeTag.findByCode(entity.value) ?: key.defaultValue
            CurrencyCodeTag::class -> CurrencyCodeTag.findByCode(entity.value) ?: key.defaultValue
            ClockInstant::class -> ClockInstant(stored.toLong())
            else -> throw IllegalStateException("Unsupported type")
        }

        return value as T
    }

    override suspend fun <T : Any> set(key: SettingItem<T>, value: T) {
        val value: String = when (value) {
            is String,
            is Int,
            is Boolean,
            is Float,
            is Long  -> value.toString()
            is CountryCodeTag -> value.code
            is CurrencyCodeTag -> value.code
            is ClockInstant -> value.utcMillis.toString()
            else -> throw IllegalStateException("Unsupported type")
        }

        dao.setRaw(SettingEntity(key.key, value))
    }

    override fun requireSettingKeySet(requireSettings: List<SettingItem<*>>): Flow<Boolean> {
        return dao.getAllSettingsFlow().map { settingList ->
            val allSetKeys = settingList.map { it.key }

            requireSettings.all { item ->
                item.key in allSetKeys
            }
        }
    }
}