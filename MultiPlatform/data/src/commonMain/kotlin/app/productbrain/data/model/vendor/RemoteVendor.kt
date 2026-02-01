package app.productbrain.data.model.vendor

import app.productbrain.common.Ulid
import kotlin.jvm.JvmInline

@JvmInline
value class RemoteVendorId(
    val value: String
) {
    companion object {
        fun create() = RemoteVendorId(Ulid.create().value)
    }
}

data class RemoteVendor(
    val id: RemoteVendorId,
    val name: String,
    val alias: List<String>
)