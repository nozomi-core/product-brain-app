package app.productbrain.data.common

enum class CurrencyCode(
    val code: String
) {
    AUD("AUD"),
    JPY("JPY"),
    USD("USD");

    companion object {
        fun findByCode(code: String): CurrencyCode? = entries.find { it.code == code }
    }
}