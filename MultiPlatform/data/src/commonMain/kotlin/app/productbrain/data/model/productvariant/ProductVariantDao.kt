package app.productbrain.data.model.productvariant

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ProductVariantDao {
    @Upsert
    suspend fun upsert(entityRemote: ProductVariantEntity)

    @Query("SELECT * FROM product_variant")
    suspend fun getAll(): List<ProductVariantEntity>
}