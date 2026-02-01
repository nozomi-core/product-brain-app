package app.productbrain.data.model.productunit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("product_unit")
data class ProductUnitEntity(
    @ColumnInfo("key")
    @PrimaryKey val key: String
)