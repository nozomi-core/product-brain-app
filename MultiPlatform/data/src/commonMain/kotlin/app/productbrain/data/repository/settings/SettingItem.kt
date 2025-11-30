package app.productbrain.data.repository.settings

import app.productbrain.data.ClockInstant
import app.productbrain.data.common.CountryCode
import app.productbrain.data.common.CurrencyCode
import kotlin.reflect.KClass

object SettingItem {
    val ONBOARDING_COMPLETE = SettingKey("onboarding_complete", false, Boolean::class)
    val COUNTRY_CODE = SettingKey("country_code", CountryCode.AU, CountryCode::class)
    val CURRENCY_CODE = SettingKey("currency_code", CurrencyCode.AUD, CurrencyCode::class)
    val ONBOARDING_TIME = SettingKey("onboarding_time", ClockInstant.DEFAULT, ClockInstant::class)
}

class SettingKey<T : Any>(
    val key: String,
    val defaultValue: T,
    val type: KClass<T>
)