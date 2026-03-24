package app.productbrain.data.model.productabstract

class ProductAbstractResolver {
    fun resolve(entities: Collection<ProductAbstractEntity>): Map<ProductAbstractLocalId, ProductAbstract> {
        return entities.associate { entity ->
            entity.toModel().run {
               localId to this
            }
        }
    }
}