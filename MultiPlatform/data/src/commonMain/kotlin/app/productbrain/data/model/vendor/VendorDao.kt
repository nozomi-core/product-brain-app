package app.productbrain.data.model.vendor

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface VendorDao {

    //RemoteVendor
    @Upsert
    suspend fun upsert(entity: RemoteVendorEntity)

    @Query("SELECT * FROM vendor")
    suspend fun getAll(): List<RemoteVendorEntity>

    //RemoteVendorAlias
    @Query("SELECT * FROM vendor_alias")
    suspend fun getAllAlias(): List<RemoteVendorAliasEntity>

    @Query("DELETE FROM vendor_alias WHERE vendor_id = :vendorId")
    suspend fun deleteVendorAliasByVendorId(vendorId: Long)

    @Upsert
    suspend fun upsert(entity: RemoteVendorAliasEntity)
}