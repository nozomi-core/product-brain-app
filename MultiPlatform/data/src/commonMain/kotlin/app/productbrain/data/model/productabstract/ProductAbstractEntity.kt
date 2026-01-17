package app.productbrain.data.model.productabstract

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_abstract")
data class ProductAbstractEntity(
    @ColumnInfo("localId") @PrimaryKey
    val localId: String,
    @ColumnInfo("remoteId")
    val remoteId: String?,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("units")
    val units: List<String>,
    @ColumnInfo("alias")
    val alias: List<String>
)