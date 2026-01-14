package app.productbrain.data.model.productabstract

object ProductMapper {
    fun toRemoteEntity(remote: RemoteProductAbstract): RemoteProductAbstractEntity {
        return RemoteProductAbstractEntity(
            id = remote.id.value,
            name = remote.name,
            units = remote.units.map { it.key },
            alias = remote.alias.map { it }
        )
    }
}