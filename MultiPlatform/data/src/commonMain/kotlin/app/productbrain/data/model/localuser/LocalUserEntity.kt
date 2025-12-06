package app.productbrain.data.model.localuser

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_user")
data class LocalUserEntity(
    @ColumnInfo("user_id") @PrimaryKey val userId: String,
    @ColumnInfo("created_at") val createdAt: Long
)