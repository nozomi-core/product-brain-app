package app.productbrain.data.repository.settings

object Settings {
    val USERNAME = SettingKey("username", "")
    val DARK_MODE = SettingKey("dark_mode", false)
    val FONT_SIZE = SettingKey("font_size", 14)
}

class SettingKey<T>(
    val key: String,
    val defaultValue: T
)