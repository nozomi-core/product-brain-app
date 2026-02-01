package app.productbrain.extensions

fun String.normalise(): String {
    return replace("  ", " ")
}