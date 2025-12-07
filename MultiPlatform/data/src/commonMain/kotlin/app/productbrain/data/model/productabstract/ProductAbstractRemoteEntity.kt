package app.productbrain.data.model.productabstract

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_abstract_remote")
data class ProductAbstractRemoteEntity(
    @ColumnInfo("id") @PrimaryKey
    val id: String,
    @ColumnInfo("name")
    val name: String
)