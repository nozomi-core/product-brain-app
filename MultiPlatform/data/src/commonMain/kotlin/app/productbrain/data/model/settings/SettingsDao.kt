package app.productbrain.data.model.settings

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {

    @Query("SELECT * FROM settings WHERE `key` = :key LIMIT 1")
    suspend fun getRaw(key: String): SettingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setRaw(entity: SettingEntity)

    @Query("SELECT * FROM settings")
    suspend fun getAllSettings(): List<SettingEntity>


    @Query("SELECT * FROM settings")
    fun getAllSettingsFlow(): Flow<List<SettingEntity>>
}