package app.productbrain.common

import java.math.BigDecimal
import java.math.RoundingMode

actual fun numberOf(value: String): Maybe<BigNumber> {
    return Maybe.tryResultBlocking {
        AndroidBigNumber(BigDecimal(value))
    }
}

@JvmInline
value class AndroidBigNumber(private val value: BigDecimal) : BigNumber {
    override fun plus(other: BigNumber): BigNumber {
        return when(other) {
            is AndroidBigNumber -> AndroidBigNumber(other.value.add(value))
            else -> throw Exception("JVM Type[${other.javaClass.name}] cannot be used as a BigNumber in android source")
        }
    }

    override fun toDisplay(precision: Int): String {
        val roundedValue = value.setScale(precision, RoundingMode.HALF_UP)
        return roundedValue.toString()
    }

    override fun toString(): String {
        return value.toString()
    }
}

actual fun numberOf(value: Int): BigNumber {
    return AndroidBigNumber(BigDecimal(value))
}

actual fun numberOf(value: Double): BigNumber {
    return AndroidBigNumber(BigDecimal(value))
}