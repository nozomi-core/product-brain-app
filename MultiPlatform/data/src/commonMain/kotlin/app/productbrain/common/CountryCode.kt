package app.productbrain.common

enum class CountryCodeTag(
    val code: String,
    val displayName: String
) {
    AU("AU", "Australia"),
    US("US", "United Stated"),
    CA("CA", "Canada");

    companion object {
        fun findByCode(code: String): CountryCodeTag? = entries.find { it.code == code }
    }
}