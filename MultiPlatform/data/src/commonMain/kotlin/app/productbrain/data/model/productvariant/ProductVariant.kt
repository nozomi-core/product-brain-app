package app.productbrain.data.model.productvariant

import app.productbrain.common.RemoteId
import app.productbrain.common.UlidFactory
import app.productbrain.data.model.productabstract.ProductAbstract
import app.productbrain.data.model.productabstract.ProductAbstractLocalId
import kotlin.jvm.JvmInline

@JvmInline
value class ProductVariantLocalId(
    val value: String
) {
    companion object {
        fun create(): ProductVariantLocalId = ProductVariantLocalId("LOC${UlidFactory.create().value}")
    }
}

@JvmInline
value class ProductVariantRemoteId(
    val value: String
)

data class ProductVariant(
    val localId: ProductVariantLocalId,
    val remoteId: RemoteId<ProductVariantRemoteId>,
    val parentProductId: ProductAbstractLocalId,
    val name: String,
    val parent: ProductAbstract,
    val isDefaultVariant: Boolean
)