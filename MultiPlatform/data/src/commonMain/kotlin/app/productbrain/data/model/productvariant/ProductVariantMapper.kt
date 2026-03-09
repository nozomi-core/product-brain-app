package app.productbrain.data.model.productvariant

import app.productbrain.common.Optional
import app.productbrain.common.RemoteId
import app.productbrain.data.model.productabstract.ProductAbstractLocalId

object ProductVariantMapper {
    fun toEntity(model: ProductVariant): ProductVariantEntity {
        return ProductVariantEntity(
            localId = model.localId.value,
            remoteId = model.remoteId.toIdString(),
            abstractProductId = model.parentProductId.value,
            name = model.name,
            isDefaultVariant = model.isDefaultVariant
        )
    }

    fun toModel(entity: ProductVariantEntity): ProductVariant {
        return ProductVariant(
            localId = ProductVariantLocalId(entity.localId),
            remoteId = RemoteId.fromIdString(entity.remoteId) { ProductVariantRemoteId(it) },
            parentProductId = ProductAbstractLocalId(entity.abstractProductId),
            name = entity.name,
            isDefaultVariant = entity.isDefaultVariant,
            parent = Optional.None
        )
    }
}