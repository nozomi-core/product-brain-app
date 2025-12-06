package app.productbrain.data.model.vendor

import app.productbrain.data.common.Ulid
import kotlin.jvm.JvmInline

@JvmInline
value class VendorId private constructor(
    val value: String
) {
    companion object {
        fun create() = VendorId(Ulid.create().value)
    }
}

data class Vendor(
    val id: VendorId,
    val name: String
)