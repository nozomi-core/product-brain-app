package app.productbrain.data.model.productabstract

import app.productbrain.common.Maybe
import app.productbrain.common.tryMaybe

interface ProductAbstractRepository {
    suspend fun upsert(model: ProductAbstract): Maybe<Unit>
    suspend fun getAbstractProductList(): Maybe<AbstractProductList>
}

class ProductAbstractRepositoryActual(
    private val productAbstractDao: ProductAbstractDao
): ProductAbstractRepository {
    override suspend fun upsert(model: ProductAbstract) = Maybe.tryMaybe {
        productAbstractDao.upsert(ProductAbstractMapper.toRemoteEntity(model))
    }

    override suspend fun getAbstractProductList(): Maybe<AbstractProductList> {
        return TODO("Not yet implemented")
    }
}