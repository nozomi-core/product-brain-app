package app.productbrain.feature.startup.usecase

import app.productbrain.data.model.localuser.CurrentLocalUser
import app.productbrain.data.model.localuser.LocalUserId
import app.productbrain.data.model.localuser.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCurrentLocalUserUseCase(
    private val localUserRepository: LocalUserRepository
) {
    operator fun invoke(): Flow<Result> {
        return localUserRepository.getCurrentLocalUserFlow().map {
            when(it) {
                is CurrentLocalUser -> Result.ActiveUser(it.userId)
                else -> Result.UserNotFound
            }
        }
    }


    sealed interface Result {
        object UserNotFound: Result
        class ActiveUser(val localUserId: LocalUserId): Result
    }
}