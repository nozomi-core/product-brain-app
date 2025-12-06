package app.productbrain.data.model.vendor

fun Vendor.toEntity(): VendorEntity {
    return VendorEntity(
        id = id.value,
        name = name
    )
}