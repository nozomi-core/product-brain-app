package app.productbrain.data.model.productabstract

import app.productbrain.common.Maybe
import app.productbrain.common.tryMaybe

interface ProductAbstractRepository {
    suspend fun getByLocalId(localId: ProductAbstractLocalId)
    suspend fun upsert(model: ProductAbstract): Maybe<Unit>
    suspend fun getAbstractProductList(): Maybe<AbstractProductList>
}

class ProductAbstractRepositoryActual(
    private val productAbstractDao: ProductAbstractDao
): ProductAbstractRepository {

    override suspend fun getByLocalId(localId: ProductAbstractLocalId) {
        TODO("Not yet implemented")
    }

    override suspend fun upsert(model: ProductAbstract) = Maybe.tryMaybe {
        productAbstractDao.upsert(model.toEntity())
    }

    override suspend fun getAbstractProductList(): Maybe<AbstractProductList> {
        return TODO("Not yet implemented")
    }
}