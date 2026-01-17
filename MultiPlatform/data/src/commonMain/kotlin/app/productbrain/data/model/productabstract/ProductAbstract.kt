package app.productbrain.data.model.productabstract

import app.productbrain.data.model.productunit.UnitName
import kotlin.jvm.JvmInline

@JvmInline
value class ProductAbstractLocalId(
    val value: String
)

@JvmInline
value class ProductAbstractRemoteId(
    val value: String
)

data class ProductAbstract(
    val localId: ProductAbstractLocalId,
    val remoteId: ProductAbstractRemoteId,
    val name: String,
    val units: List<UnitName>,
    val alias: List<String>
)
