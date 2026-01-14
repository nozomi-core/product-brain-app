package app.productbrain.data.model.productabstract

import app.productbrain.data.model.productunit.UnitName
import app.productbrain.data.model.productunit.UnitSystem
import kotlin.jvm.JvmInline

@JvmInline
value class RemoteProductAbstractId(
    val value: String
)

data class RemoteProductAbstract(
    val id: RemoteProductAbstractId,
    val name: String,
    val units: List<UnitName>,
    val alias: List<String>
)
