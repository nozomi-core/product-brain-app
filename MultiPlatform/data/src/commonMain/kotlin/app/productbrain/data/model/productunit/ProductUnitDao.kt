package app.productbrain.data.model.productunit

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface ProductUnitDao {

    @Upsert
    suspend fun upsert(entity: ProductUnitEntity)
}