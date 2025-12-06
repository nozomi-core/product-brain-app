package app.productbrain.data.model.localuser

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_user")
data class LocalUserEntity(
    @PrimaryKey val userId: String,
    val createdAt: Long
)