package app.productbrain.data.model.localuser

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalUserDao {

    /* Table/LocalUserEntity */
    @Upsert
    suspend fun upsert(entity: LocalUserEntity)
    @Query("SELECT * from local_user")
    fun getLocalUserFlow(): Flow<LocalUserEntity>

    /* Table/CurrentLocalUserEntity */
    @Query("SELECT * from current_local_user WHERE `key` = 'active'")
    fun getCurrentLocalUser(): Flow<CurrentLocalUserEntity?>

    @Upsert
    fun setCurrentLocalUser(currentLocalUserEntity: CurrentLocalUserEntity)
}