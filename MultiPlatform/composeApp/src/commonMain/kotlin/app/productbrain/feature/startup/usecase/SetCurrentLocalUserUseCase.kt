package app.productbrain.feature.startup.usecase

import app.productbrain.data.lang.Maybe
import app.productbrain.data.repository.localuser.LocalUser
import app.productbrain.data.repository.localuser.LocalUserRepository
class SetCurrentLocalUserUseCase(
    private val localUserRepository: LocalUserRepository
) {

    suspend operator fun invoke(user: LocalUser): Maybe<Result> {
        return Maybe.tryResult {
            localUserRepository.setCurrentLocalUser(user)
            Result.Ok
        }
    }

    sealed interface Result {
        object Ok: Result
    }
}