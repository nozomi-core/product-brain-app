package app.productbrain.data.model.vendor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "vendor")
data class VendorEntity(
    @ColumnInfo("local_id") @PrimaryKey val localId: String,
    @ColumnInfo("remote_id") val remoteId: String?,
    @ColumnInfo("name") val name: String
)

@Entity(
    tableName = "vendor_alias",
    foreignKeys = [
        ForeignKey(
            entity = VendorEntity::class,
            parentColumns = arrayOf("local_id"),
            childColumns = arrayOf("vendor_local_id"),
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class VendorAliasEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("vendor_local_id") val vendorLocalId: String,
    @ColumnInfo("alias") val alias: String
)