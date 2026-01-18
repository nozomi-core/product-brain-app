package app.productbrain.data.model.productabstract

import app.productbrain.common.Maybe
import app.productbrain.common.tryMaybe

interface ProductAbstractRepository {
    suspend fun upsert(remote: ProductAbstract): Maybe<Unit>
    suspend fun getAbstractProductList(): Maybe<AbstractProductList>
    suspend fun syncRemoteProductList(): Maybe<Unit>
}

class ProductAbstractRepositoryActual(
    private val productAbstractDao: ProductAbstractDao
): ProductAbstractRepository {
    override suspend fun upsert(remote: ProductAbstract) = Maybe.tryMaybe {
        productAbstractDao.upsert(ProductAbstractMapper.toRemoteEntity(remote))
    }

    override suspend fun getAbstractProductList(): Maybe<AbstractProductList> {
        return TODO("Not yet implemented")
    }

    override suspend fun syncRemoteProductList(): Maybe<Unit> {
        return tryMaybe {
            allProducts.build().products.forEach {
                val entity = ProductAbstractMapper.toRemoteEntity(it)
                productAbstractDao.upsert(entity)
            }
        }
    }
}