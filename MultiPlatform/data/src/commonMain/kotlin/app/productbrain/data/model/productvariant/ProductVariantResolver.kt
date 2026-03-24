package app.productbrain.data.model.productvariant

import app.productbrain.common.Optional
import app.productbrain.data.model.productabstract.ProductAbstractDao
import app.productbrain.data.model.productabstract.ProductAbstractResolver

class ProductVariantResolver(
    private val abstractProductAbstractDao: ProductAbstractDao,
    private val abstractResolver: ProductAbstractResolver
) {

    suspend fun resolve(entities: Collection<ProductVariantEntity>): Map<ProductVariantLocalId, ProductVariant> {
        val productEntities = abstractProductAbstractDao.getByIds(entities.map { it.abstractProductId })
            .run { abstractResolver.resolve(this) }

        return entities.associate { entity ->
            val productParent = productEntities[entity.abstractProductId]
            entity.toModel(Optional.create(productParent)).run {
                localId to this
            }
        }
    }
}