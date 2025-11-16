package app.productbrain.data.repository.login

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface LoginDao {
    @Insert
    suspend fun insert(loginEntryEntity: LoginEntryEntity)
}