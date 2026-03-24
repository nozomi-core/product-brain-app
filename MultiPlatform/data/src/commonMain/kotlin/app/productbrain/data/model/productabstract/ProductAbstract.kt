package app.productbrain.data.model.productabstract

import app.productbrain.common.RemoteId
import app.productbrain.common.createLocalId
import app.productbrain.data.model.productunit.UnitSystem
import kotlin.jvm.JvmInline

@JvmInline
value class ProductAbstractLocalId(
    val value: String
) {
    companion object {
        fun create(): ProductAbstractLocalId = ProductAbstractLocalId(createLocalId())
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
    val unitSystem: List<UnitSystem>,
    val alias: List<String>
)
