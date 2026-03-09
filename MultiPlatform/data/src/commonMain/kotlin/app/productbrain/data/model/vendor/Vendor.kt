package app.productbrain.data.model.vendor

import app.productbrain.common.RemoteId
import app.productbrain.common.Ulid
import app.productbrain.common.UlidFactory
import app.productbrain.common.createLocalId
import kotlin.jvm.JvmInline

@JvmInline
value class VendorLocalId(
    val value: String
) {
    companion object {
        fun create(): VendorLocalId = VendorLocalId(createLocalId())
    }

    override fun toString(): String {
        return value
    }
}

@JvmInline
value class VendorRemoteId(
    val value: String
) {
    override fun toString(): String {
        return value
    }
}

data class Vendor(
    val localId: VendorLocalId,
    val remoteId: RemoteId<VendorRemoteId>,
    val name: String,
    val alias: List<String>
)