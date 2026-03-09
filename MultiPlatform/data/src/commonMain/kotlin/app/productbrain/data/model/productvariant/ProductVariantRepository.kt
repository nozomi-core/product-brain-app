package app.productbrain.data.model.productvariant

import app.productbrain.common.Maybe
import app.productbrain.common.tryMaybe

interface ProductVariantRepository {
    suspend fun upsert(model: ProductVariant): Maybe<Unit>
    suspend fun queryAll(): Maybe<List<ProductVariant>>
}

class ProductVariantRepositoryActual(
    private val productVariantDao: ProductVariantDao
): ProductVariantRepository {
    override suspend fun upsert(model: ProductVariant): Maybe<Unit> = tryMaybe {
        productVariantDao.upsert(ProductVariantMapper.toEntity(model))
    }

    override suspend fun queryAll(): Maybe<List<ProductVariant>> {
        return tryMaybe {
            productVariantDao.getAll().map { ProductVariantMapper.toModel(it) }
        }
    }
}