package app.productbrain.data.model.vendor

object VendorMapper {
    fun toRemoteVendorModel(
        entity: RemoteVendorEntity,
        alias: List<String>
    ): RemoteVendor {
        return RemoteVendor(
            id = RemoteVendorId(entity.id),
            name = entity.name,
            alias = alias
        )
    }

    fun toRemoteVendorEntity(vendor: RemoteVendor): RemoteVendorEntity {
        return RemoteVendorEntity(
            id =  vendor.id.value,
            name = vendor.name
        )
    }

    fun toRemoteVendorAliasEntityList(vendor: RemoteVendor): List<RemoteVendorAliasEntity> {
        return vendor.alias.map {
            RemoteVendorAliasEntity(
                vendorId = vendor.id.value,
                alias = it
            )
        }
    }
}