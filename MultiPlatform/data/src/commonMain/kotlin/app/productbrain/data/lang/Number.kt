package app.productbrain.data.lang

expect fun numberOf(value: String): Maybe<BigNumber>
expect fun numberOf(value: Int): BigNumber

interface BigNumber {
    operator fun plus(other: BigNumber): BigNumber
}

object Numbers {
    val Zero = numberOf(0)
}