package app.productbrain.data.model.localuser

import app.productbrain.data.ClockInstant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface LocalUserRepository {

    suspend fun upsert(user: LocalUser)
    fun getLocalUserFlow(): Flow<LocalUser>

    fun getCurrentLocalUserFlow(): Flow<CurrentLocalUser?>
    suspend fun setCurrentLocalUser(user: LocalUser)
}

class LocalUserRepositoryActual(
    private val localUserDao: LocalUserDao
): LocalUserRepository {
    override suspend fun upsert(user: LocalUser) {
        localUserDao.upsert(
            LocalUserEntity(
                userId = user.userId.id,
                createdAt = user.createdAt.utcMillis
            )
        )
    }

    override fun getLocalUserFlow(): Flow<LocalUser> {
        return localUserDao.getLocalUserFlow().map {
            LocalUser(
                userId = LocalUserId(it.userId),
                createdAt = ClockInstant(it.createdAt)
            )
        }
    }

    override fun getCurrentLocalUserFlow(): Flow<CurrentLocalUser?> {
        return localUserDao.getCurrentLocalUser().map { entity ->
            if(entity != null) {
                CurrentLocalUser(LocalUserId(entity.userId))
            } else null
        }
    }

    override suspend fun setCurrentLocalUser(user: LocalUser) {
        localUserDao.setCurrentLocalUser(
            CurrentLocalUserEntity(
                key = "active",
                userId = user.userId.id
            )
        )
    }
}