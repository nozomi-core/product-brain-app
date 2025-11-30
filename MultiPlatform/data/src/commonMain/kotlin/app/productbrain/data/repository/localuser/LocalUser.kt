package app.productbrain.data.repository.localuser

import app.productbrain.data.ClockInstant

data class LocalUser(
    val userId: LocalUserId,
    val createdAt: ClockInstant
)