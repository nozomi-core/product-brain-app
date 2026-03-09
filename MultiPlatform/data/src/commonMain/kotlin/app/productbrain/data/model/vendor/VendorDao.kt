package app.productbrain.data.model.vendor

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface VendorDao {

    //RemoteVendor
    @Upsert
    suspend fun upsert(entity: VendorEntity)

    @Query("SELECT * FROM vendor")
    suspend fun getAll(): List<VendorEntity>

    //RemoteVendorAlias
    @Query("SELECT * FROM vendor_alias")
    suspend fun getAllAlias(): List<VendorAliasEntity>

    @Query("DELETE FROM vendor_alias WHERE vendor_local_id = :vendorLocalId")
    suspend fun deleteVendorAliasByVendorId(vendorLocalId: Long)

    @Upsert
    suspend fun upsert(entity: VendorAliasEntity)
}