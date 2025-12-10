package app.productbrain.data.model.productabstract

import app.productbrain.common.Maybe

interface ProductAbstractRepository {
    suspend fun upsert(remote: ProductAbstractRemote): Maybe<Unit>
}

class ProductAbstractRepositoryActual(
    private val productAbstractDao: ProductAbstractDao
): ProductAbstractRepository {
    override suspend fun upsert(remote: ProductAbstractRemote) = Maybe.tryMaybe {
        val entity = ProductAbstractRemoteEntity(
            id = remote.id.value,
            name = remote.name
        )
        productAbstractDao.upsert(entity)
    }
}