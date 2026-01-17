package app.productbrain.data.model.productabstract

import app.productbrain.common.Maybe
import app.productbrain.common.tryMaybe

interface ProductAbstractRepository {
    suspend fun upsert(remote: ProductAbstract): Maybe<Unit>
    suspend fun getRemoteProductList(): Maybe<RemoteAbstractProductList>
    suspend fun syncRemoteProductList(): Maybe<Unit>
}

class ProductAbstractRepositoryActual(
    private val productAbstractDao: ProductAbstractDao
): ProductAbstractRepository {
    override suspend fun upsert(remote: ProductAbstract) = Maybe.tryMaybe {
        productAbstractDao.upsert(ProductAbstractMapper.toRemoteEntity(remote))
    }

    override suspend fun getRemoteProductList(): Maybe<RemoteAbstractProductList> {
        //TODO("Not yet implemented")
        return Maybe.of(RemoteAbstractProductList())
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