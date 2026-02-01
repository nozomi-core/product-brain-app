package app.productbrain.data.model.productabstract

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_abstract")
data class ProductAbstractEntity(
    @ColumnInfo("local_id") @PrimaryKey
    val localId: String,
    @ColumnInfo("remote_id")
    val remoteId: String?,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("units")
    val units: List<String>,
    @ColumnInfo("alias")
    val alias: List<String>
)