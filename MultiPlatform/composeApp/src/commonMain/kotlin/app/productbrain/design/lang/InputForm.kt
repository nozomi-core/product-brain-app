package app.productbrain.design.lang

sealed class InputForm<out T>(
    val text: String,
    val lastValue: T
) {
    class Valid<T>(text: String, val value: T) : InputForm<T>(text, value)
    class Invalid<T>(text: String, lastValue: T) : InputForm<T>(text, lastValue)
}
