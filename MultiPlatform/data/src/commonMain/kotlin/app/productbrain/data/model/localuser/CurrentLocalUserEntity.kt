package app.productbrain.data.model.localuser

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_local_user")
data class CurrentLocalUserEntity(
    @PrimaryKey val key: String,
    val userId: String
)