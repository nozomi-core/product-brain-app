package app.productbrain.feature.startup.usecase

import app.productbrain.common.Maybe
import app.productbrain.data.model.localuser.LocalUser
import app.productbrain.data.model.localuser.LocalUserRepository
class SetCurrentLocalUserUseCase(
    private val localUserRepository: LocalUserRepository
) {

    suspend operator fun invoke(user: LocalUser): Maybe<Result> {
        return Maybe.tryMaybe {
            localUserRepository.setCurrentLocalUser(user)
            Result.Ok
        }
    }

    sealed interface Result {
        object Ok: Result
    }
}