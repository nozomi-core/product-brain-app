package app.productbrain.data.model.productvariant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import app.productbrain.data.model.productabstract.ProductAbstractEntity

@Entity(
    tableName = "product_variant",
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
    val abstractProductId: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("is_default")
    val isDefaultVariant: Boolean
)

/*
@Entity(
    tableName = "product_variant_alias",
    foreignKeys = [
        ForeignKey(
            entity = ProductVariantEntity::class,
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
)*/