package app.productbrain.data.model.vendor

import app.productbrain.common.RemoteId

object VendorMapper {
    fun toRemoteVendorModel(
        entity: VendorEntity,
        alias: List<String>
    ): Vendor {
        return Vendor(
            localId = VendorLocalId(entity.localId),
            remoteId = RemoteId.fromIdString(entity.remoteId) { VendorRemoteId(it) },
            name = entity.name,
            alias = alias
        )
    }

    fun toRemoteVendorEntity(vendor: Vendor): VendorEntity {
        return VendorEntity(
            localId = vendor.localId.value,
            remoteId = vendor.remoteId.toIdString(),
            name = vendor.name
        )
    }

    fun toRemoteVendorAliasEntityList(vendor: Vendor): List<VendorAliasEntity> {
        return vendor.alias.map {
            VendorAliasEntity(
                vendorLocalId = vendor.localId.value,
                alias = it
            )
        }
    }
}