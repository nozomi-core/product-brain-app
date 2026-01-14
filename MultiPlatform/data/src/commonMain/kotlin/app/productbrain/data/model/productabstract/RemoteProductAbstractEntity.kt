package app.productbrain.data.model.productabstract

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_product_abstract")
data class RemoteProductAbstractEntity(
    @ColumnInfo("id") @PrimaryKey
    val id: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("units")
    val units: List<String>,
    @ColumnInfo("alias")
    val alias: List<String>
)