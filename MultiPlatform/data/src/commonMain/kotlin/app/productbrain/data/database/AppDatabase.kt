package app.productbrain.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import app.productbrain.data.repository.localuser.CurrentLocalUser
import app.productbrain.data.repository.localuser.CurrentLocalUserEntity
import app.productbrain.data.repository.settings.SettingEntity
import app.productbrain.data.repository.settings.SettingsDao
import app.productbrain.data.repository.localuser.LocalUserDao
import app.productbrain.data.repository.localuser.LocalUserEntity

@Database(
    entities =
        [
            SettingEntity::class,
            LocalUserEntity::class,
            CurrentLocalUserEntity::class
        ],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
    abstract fun localUserDao(): LocalUserDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}