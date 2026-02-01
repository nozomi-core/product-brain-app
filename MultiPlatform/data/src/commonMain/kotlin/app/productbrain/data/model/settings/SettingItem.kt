package app.productbrain.data.model.settings

import app.productbrain.data.ClockInstant
import app.productbrain.common.CountryCodeTag
import app.productbrain.common.CurrencyCodeTag
import app.productbrain.common.LanguageCodeTag
import kotlin.reflect.KClass

sealed class SettingItem<T : Any>(
    val key: String,
    val defaultValue: T,
    val type: KClass<T>
) {
    object OnBoardingComplete: SettingItem<Boolean>("onboarding_complete", false, Boolean::class)
    object CountryCode: SettingItem<CountryCodeTag>("country_code", CountryCodeTag.AU, CountryCodeTag::class)
    object CurrencyCode: SettingItem<CurrencyCodeTag>("currency_code", CurrencyCodeTag.AUD, CurrencyCodeTag::class)
    object OnBoardingTime: SettingItem<ClockInstant>("onboarding_time", ClockInstant.DEFAULT, ClockInstant::class)
    object LanguageCode: SettingItem<LanguageCodeTag>("language_code", LanguageCodeTag.EN, LanguageCodeTag::class)
}