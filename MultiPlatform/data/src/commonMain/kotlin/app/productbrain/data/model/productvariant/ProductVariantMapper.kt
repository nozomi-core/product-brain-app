package app.productbrain.data.model.productvariant

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
}