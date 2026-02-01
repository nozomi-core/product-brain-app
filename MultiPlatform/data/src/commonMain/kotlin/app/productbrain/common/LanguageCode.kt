package app.productbrain.common

enum class LanguageCodeTag(
    val code: String,
    val displayName: String
) {
    EN("en", "English"),
    JP("jp", "Japanese");

    companion object {
        fun findByCode(code: String): LanguageCodeTag? = entries.find { it.code == code }
    }
}