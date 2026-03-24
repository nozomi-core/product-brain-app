package app.productbrain.data.model.productabstract

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_abstract",
    indices = [Index(value = ["name"], unique = true)]
)
data class ProductAbstractEntity(
    @ColumnInfo("local_id") @PrimaryKey
    val localId: ProductAbstractLocalId,
    @ColumnInfo("remote_id")
    val remoteId: String?,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("unit_system")
    val unitSystem: List<String>,
    @ColumnInfo("alias")
    val alias: List<String>
)

