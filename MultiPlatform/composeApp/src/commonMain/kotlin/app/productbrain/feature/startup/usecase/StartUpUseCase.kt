package app.productbrain.feature.startup.usecase

import app.productbrain.feature.startup.service.StartupService
import kotlinx.coroutines.flow.first

class StartUpUseCase(
    private val doesUserNeedOnboarding: IsUserOnBoardedUseCase,
    private val getCurrentLocalUserUseCase: GetCurrentLocalUserUseCase
) {
    suspend operator fun invoke(): StartupService.State {
        val doesUserNeedOnboarding = doesUserNeedOnboarding()
        val currentUser = getCurrentLocalUserUseCase().first()

        return StartupService.State.StartupComplete(
            isOnboarded = doesUserNeedOnboarding,
            currentUser = currentUser
        )
    }
}
