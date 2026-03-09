package app.productbrain.design.lang

sealed class InputForm<out T>(
    val text: String,
    val lastValue: T
) {
    class Valid<T>(text: String, val value: T) : InputForm<T>(text, value)
    class Invalid<T>(text: String, lastValue: T) : InputForm<T>(text, lastValue)

    fun getOrLast(): T = when(this) {
        is Valid -> value
        is Invalid -> lastValue
    }

    override fun toString(): String {
        return when(this) {
            is Invalid<*> -> "Invalid#${this.text}"
            is Valid<*> -> "Valid#${this.text}"
        }
    }
}
