package app.productbrain.data.repository.login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_entry")
data class LoginEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val instant: Long
)