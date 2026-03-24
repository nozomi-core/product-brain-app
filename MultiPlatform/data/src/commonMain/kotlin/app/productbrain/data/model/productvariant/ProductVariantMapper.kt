package app.productbrain.data.model.productvariant

import app.productbrain.common.Optional
import app.productbrain.common.RemoteId
import app.productbrain.data.model.productabstract.ProductAbstract
import app.productbrain.data.model.productabstract.ProductAbstractLocalId

fun ProductVariant.toEntity(): ProductVariantEntity {
    return ProductVariantEntity(
        localId = localId.value,
        remoteId = remoteId.toIdString(),
        abstractProductId = parentProductId,
        name = name,
        isDefaultVariant = isDefaultVariant
    )
}

fun ProductVariantEntity.toModel(
    parent: Optional<ProductAbstract> = Optional.None
): ProductVariant {
    return ProductVariant(
        localId = ProductVariantLocalId(localId),
        remoteId = RemoteId.fromIdString(remoteId) { ProductVariantRemoteId(it) },
        parentProductId = abstractProductId,
        name = name,
        isDefaultVariant = isDefaultVariant,
        parent = parent
    )
}