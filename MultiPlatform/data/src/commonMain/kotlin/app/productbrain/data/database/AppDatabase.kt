package app.productbrain.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import app.productbrain.data.model.localuser.CurrentLocalUserEntity
import app.productbrain.data.model.settings.SettingEntity
import app.productbrain.data.model.settings.SettingsDao
import app.productbrain.data.model.localuser.LocalUserDao
import app.productbrain.data.model.localuser.LocalUserEntity
import app.productbrain.data.model.productabstract.ProductAbstractDao
import app.productbrain.data.model.productabstract.ProductAbstractEntity
import app.productbrain.data.model.productunit.ProductUnitDao
import app.productbrain.data.model.productunit.ProductUnitEntity
import app.productbrain.data.model.productvariant.ProductVariantDao
import app.productbrain.data.model.productvariant.ProductVariantEntity
import app.productbrain.data.model.vendor.RemoteVendorAliasEntity
import app.productbrain.data.model.vendor.VendorDao
import app.productbrain.data.model.vendor.RemoteVendorEntity

@Database(
    entities =
        [
            SettingEntity::class,
            LocalUserEntity::class,
            CurrentLocalUserEntity::class,
            RemoteVendorEntity::class,
            RemoteVendorAliasEntity::class,
            ProductUnitEntity::class,
            ProductAbstractEntity::class,
            ProductVariantEntity::class
        ],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(FieldConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
    abstract fun localUserDao(): LocalUserDao
    abstract fun vendorDao(): VendorDao
    abstract fun productUnitDao(): ProductUnitDao
    abstract fun productAbstractDao(): ProductAbstractDao
    abstract fun productVariantDao(): ProductVariantDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}