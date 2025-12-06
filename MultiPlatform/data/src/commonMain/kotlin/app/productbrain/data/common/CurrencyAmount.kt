package app.productbrain.data.common

import app.productbrain.data.lang.BigNumber

data class CurrencyAmount(
    val amount: BigNumber,
    val code: CurrencyCodeTag
)
