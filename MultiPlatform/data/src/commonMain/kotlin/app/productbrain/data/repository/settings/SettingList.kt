package app.productbrain.data.repository.settings

import app.productbrain.data.common.CountryCode

object SettingList {
    val ONBOARDING_COMPLETE = SettingKey("onboarding_complete", false)
    val COUNTRY_CODE = SettingKey("country_code", CountryCode.AU)
}

class SettingKey<T>(
    val key: String,
    val defaultValue: T
)