package app.productbrain.data.model.productabstract

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
abstract class ProductAbstractDao {

    @Upsert
    abstract suspend fun upsert(entityRemote: ProductAbstractEntity)

    suspend fun getByIds(ids: List<ProductAbstractLocalId>): List<ProductAbstractEntity>  {
        return ids.chunked(100).flatMap { chunkIds(it) }
    }

    @Query("select * from product_abstract where local_id in (:ids) ")
    abstract suspend fun chunkIds(ids: List<ProductAbstractLocalId>): List<ProductAbstractEntity>

}