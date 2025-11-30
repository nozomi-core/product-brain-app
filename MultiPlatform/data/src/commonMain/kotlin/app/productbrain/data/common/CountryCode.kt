package app.productbrain.data.common

enum class CountryCode(
    val code: String,
    val displayName: String
) {
    AU("AU", "Australia"),
    US("US", "United Stated"),
    CA("CA", "Canada");
    companion object {
        fun findByCode(code: String): CountryCode? = entries.find { it.code == code }
    }
}