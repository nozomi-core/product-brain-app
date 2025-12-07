package app.productbrain.data.model.vendor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vendor")
data class VendorEntity(
    @ColumnInfo("id") @PrimaryKey val id: String,
    @ColumnInfo("name") val name: String
)