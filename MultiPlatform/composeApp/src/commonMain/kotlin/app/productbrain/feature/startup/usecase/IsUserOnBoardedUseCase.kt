package app.productbrain.feature.startup.usecase

import app.productbrain.data.repository.settings.SettingItem
import app.productbrain.data.repository.settings.SettingsRepository

class IsUserOnBoardedUseCase(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): Result {
        val isOnboarded = settingsRepository.get(SettingItem.OnBoardingComplete)
        return if(isOnboarded) {
            Result.OnBoardingComplete
        } else {
            Result.OnBoardingRequired
        }
    }

    sealed interface Result {
        data object OnBoardingComplete: Result
        data object OnBoardingRequired: Result
    }
}