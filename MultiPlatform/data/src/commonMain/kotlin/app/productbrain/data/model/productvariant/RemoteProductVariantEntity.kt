package app.productbrain.data.model.productvariant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import app.productbrain.data.model.productabstract.ProductAbstractEntity

@Entity(
    tableName = "remote_product_variant",
    foreignKeys = [
        ForeignKey(
            entity = ProductAbstractEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("product_abstract_id"),
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class RemoteProductVariantEntity(
    @ColumnInfo("id") @PrimaryKey
    val id: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("product_abstract_id")
    val productAbstractId: String,
    @ColumnInfo("is_default")
    val isDefaultVariant: Boolean
)


@Entity(
    tableName = "remote_variant_alias",
    foreignKeys = [
        ForeignKey(
            entity = RemoteProductVariantEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("variant_alias"),
            onDelete = ForeignKey.NO_ACTION,
            onUpdate = ForeignKey.CASCADE
        )
    ])
data class RemoteVariantAliasEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("variant_id") val variantId: String,
    @ColumnInfo("alias") val alias: String
)