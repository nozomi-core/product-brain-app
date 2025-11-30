package app.productbrain.data.common

enum class CurrencyCodeTag(
    val code: String
) {
    AUD("AUD"),
    JPY("JPY"),
    USD("USD");

    companion object {
        fun findByCode(code: String): CurrencyCodeTag? = entries.find { it.code == code }
    }
}