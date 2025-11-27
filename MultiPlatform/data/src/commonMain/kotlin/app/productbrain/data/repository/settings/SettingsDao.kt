package app.productbrain.data.repository.settings

import androidx.room.*

@Dao
interface SettingsDao {

    @Query("SELECT * FROM settings WHERE `key` = :key LIMIT 1")
    suspend fun getRaw(key: String): SettingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setRaw(entity: SettingEntity)
}