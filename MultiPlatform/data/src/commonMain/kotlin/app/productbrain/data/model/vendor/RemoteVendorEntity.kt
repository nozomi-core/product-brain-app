package app.productbrain.data.model.vendor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "vendor")
data class RemoteVendorEntity(
    @ColumnInfo("id") @PrimaryKey val id: String,
    @ColumnInfo("name") val name: String
)

@Entity(
    tableName = "vendor_alias",
    foreignKeys = [
        ForeignKey(
            entity = RemoteVendorEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("vendor_id"),
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class RemoteVendorAliasEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("vendor_id") val vendorId: String,
    @ColumnInfo("alias") val alias: String
)