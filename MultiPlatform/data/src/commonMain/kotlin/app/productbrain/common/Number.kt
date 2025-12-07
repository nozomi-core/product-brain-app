package app.productbrain.common

expect fun numberOf(value: String): Maybe<BigNumber>
expect fun numberOf(value: Int): BigNumber
expect fun numberOf(value: Double): BigNumber

interface BigNumber {
    operator fun plus(other: BigNumber): BigNumber
    fun toDisplay(precision: Int = 2): String

    companion object {
        val Zero = numberOf(0)

        fun of(amount: Double): BigNumber = numberOf(amount)
        fun of(amount: String): Maybe<BigNumber> = numberOf(amount)
    }
}