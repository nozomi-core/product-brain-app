package app.productbrain.data.model.productvariant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import app.productbrain.data.model.productabstract.ProductAbstractEntity
import app.productbrain.data.model.productabstract.ProductAbstractLocalId

@Entity(
    tableName = "product_variant",
    indices = [Index(value = ["name"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = ProductAbstractEntity::class,
            parentColumns = arrayOf("local_id"),
            childColumns = arrayOf("abstract_product_id"),
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ProductVariantEntity(
    @ColumnInfo("local_id") @PrimaryKey
    val localId: String,
    @ColumnInfo("remote_id")
    val remoteId: String?,
    @ColumnInfo("abstract_product_id")
    val abstractProductId: ProductAbstractLocalId,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("is_default")
    val isDefaultVariant: Boolean
)