package app.productbrain.data.model.productvariant

import app.productbrain.common.Maybe
import app.productbrain.common.tryMaybe

interface ProductVariantRepository {
    suspend fun upsert(model: ProductVariant): Maybe<Unit>
    suspend fun getAll(): Maybe<List<ProductVariant>>
}

class ProductVariantRepositoryActual(
    private val productVariantDao: ProductVariantDao,
    private val productVariantResolver: ProductVariantResolver
): ProductVariantRepository {
    override suspend fun upsert(model: ProductVariant): Maybe<Unit> = tryMaybe {
        productVariantDao.upsert(model.toEntity())
    }

    override suspend fun getAll(): Maybe<List<ProductVariant>> {
        return tryMaybe {
            productVariantResolver.resolve(productVariantDao.getAll()).values.toList()
        }
    }
}