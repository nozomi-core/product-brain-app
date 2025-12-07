package app.productbrain.data.model.productabstract

import kotlin.jvm.JvmInline

@JvmInline
value class ProductAbstractId(
    val value: String
)

data class ProductAbstractRemote(
    val id: ProductAbstractId,
    val name: String
)
