package app.productbrain.feature.startup.usecase

import app.productbrain.common.Maybe
import app.productbrain.common.UlidFactory
import app.productbrain.data.provider.ClockProvider
import app.productbrain.data.model.localuser.LocalUser
import app.productbrain.data.model.localuser.LocalUserId
import app.productbrain.data.model.localuser.LocalUserRepository

class CreateLocalUserUseCase(
    private val localUserRepository: LocalUserRepository,
    private val clockProvider: ClockProvider
) {
    suspend operator fun invoke(): Maybe<LocalUser> {
        return Maybe.tryResult {
            val localUser = LocalUser(
                userId = LocalUserId(UlidFactory.create().value),
                createdAt = clockProvider.now()
            )
            localUserRepository.upsert(localUser)
            localUser
        }
    }
}