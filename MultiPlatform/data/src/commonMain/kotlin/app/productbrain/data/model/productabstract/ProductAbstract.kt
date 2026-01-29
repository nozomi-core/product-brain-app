package app.productbrain.data.model.productabstract

import app.productbrain.common.RemoteId
import app.productbrain.common.UlidFactory
import app.productbrain.data.model.productunit.UnitName
import kotlin.jvm.JvmInline

@JvmInline
value class ProductAbstractLocalId(
    val value: String
) {
    companion object {
        fun create(): ProductAbstractLocalId = ProductAbstractLocalId("LOC${UlidFactory.create().value}")
    }

    override fun toString(): String {
        return value
    }
}

@JvmInline
value class ProductAbstractRemoteId(
    val value: String
) {
    override fun toString(): String {
        return value
    }
}

data class ProductAbstract(
    val localId: ProductAbstractLocalId,
    val remoteId: RemoteId<ProductAbstractRemoteId>,
    val name: String,
    val units: List<UnitName>,
    val alias: List<String>
)
