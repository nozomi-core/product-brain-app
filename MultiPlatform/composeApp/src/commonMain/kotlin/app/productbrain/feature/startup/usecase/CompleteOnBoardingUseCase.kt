package app.productbrain.feature.startup.usecase

import app.productbrain.data.provider.ClockProvider
import app.productbrain.data.repository.settings.SettingItem
import app.productbrain.data.repository.settings.SettingsRepository

class CompleteOnBoardingUseCase(
    private val settingsRepository: SettingsRepository,
    private val clockProvider: ClockProvider
) {

    suspend operator fun invoke() {
        settingsRepository.set(SettingItem.ONBOARDING_COMPLETE, true)
        settingsRepository.set(SettingItem.ONBOARDING_TIME, clockProvider.now())
    }
}