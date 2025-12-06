package app.productbrain.data.model.vendor

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface VendorDao {

    @Upsert
    suspend fun upsert(entity: VendorEntity)
}