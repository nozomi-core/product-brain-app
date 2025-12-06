package app.productbrain.data.model.localuser

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_local_user")
data class CurrentLocalUserEntity(
    @ColumnInfo("key") @PrimaryKey val key: String,
    @ColumnInfo("user_id") val userId: String
)