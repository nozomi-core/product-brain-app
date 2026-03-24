package app.productbrain.data.model.productabstract

import app.productbrain.common.RemoteId
import app.productbrain.data.model.productunit.UnitSystem

fun ProductAbstract.toEntity(): ProductAbstractEntity {
    return ProductAbstractEntity(
        localId = localId,
        remoteId = remoteId.toIdString(),
        name = name,
        unitSystem = unitSystem.map { it.id },
        alias = alias.map { it }
    )
}

fun ProductAbstractEntity.toModel(): ProductAbstract {
    return ProductAbstract(
        localId = localId,
        remoteId = RemoteId.fromIdString(remoteId) { ProductAbstractRemoteId(it) },
        name = name,
        unitSystem = unitSystem.map { UnitSystem.of(it) },
        alias = alias
    )
}