package app.productbrain.data.model.productabstract

object ProductAbstractMapper {
    fun toRemoteEntity(remote: ProductAbstract): ProductAbstractEntity {
        return ProductAbstractEntity(
            localId = remote.localId.value,
            name = remote.name,
            units = remote.units.map { it.key },
            alias = remote.alias.map { it },
            remoteId = TODO()
        )
    }
}