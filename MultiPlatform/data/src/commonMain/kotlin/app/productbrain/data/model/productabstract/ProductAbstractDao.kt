package app.productbrain.data.model.productabstract

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface ProductAbstractDao {

    @Upsert
    suspend fun upsert(entityRemote: RemoteProductAbstractEntity)
}