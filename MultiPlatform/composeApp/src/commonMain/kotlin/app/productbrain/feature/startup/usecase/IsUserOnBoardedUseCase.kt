package app.productbrain.feature.startup.usecase

import app.productbrain.data.repository.settings.SettingList
import app.productbrain.data.repository.settings.SettingsRepository

class DoesUserNeedOnboardingUseCase(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(): Result {
        val isOnboarded = settingsRepository.get(SettingList.ONBOARDING_COMPLETE)
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