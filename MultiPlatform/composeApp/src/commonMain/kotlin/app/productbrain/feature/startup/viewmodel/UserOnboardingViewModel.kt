package app.productbrain.feature.startup.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.productbrain.data.common.CountryCode
import app.productbrain.data.common.CurrencyCode
import app.productbrain.data.repository.settings.SettingItem
import app.productbrain.data.repository.settings.SettingsRepository
import app.productbrain.di.viewModelModule
import app.productbrain.feature.startup.usecase.CompleteOnBoardingUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserOnboardingViewModel(
    private val completeOnBoardingUseCase: CompleteOnBoardingUseCase,
    private val settingsRepository: SettingsRepository
): ViewModel() {
    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    val viewState = settingsRepository.requireSettingKeySet(
        listOf(SettingItem.CURRENCY_CODE, SettingItem.COUNTRY_CODE)
    )

    fun sendAction(intent: Action) {
        when(intent) {
            is Action.FinishOnboarding -> doFinishOnboarding()
            is Action.SetDefault -> doSetDefault()
        }
    }

    private fun doFinishOnboarding() {
        viewModelScope.launch {
            completeOnBoardingUseCase()
            _effect.send(Effect.OnBoardingCompleted)
        }
    }

    private fun doSetDefault() {
        viewModelScope.launch {
            settingsRepository.set(SettingItem.CURRENCY_CODE, CurrencyCode.AUD)
            settingsRepository.set(SettingItem.COUNTRY_CODE, CountryCode.AU)
        }
    }


    sealed class Action {
        object FinishOnboarding: Action()
        object SetDefault: Action()
    }

    sealed class Effect {
        object OnBoardingCompleted: Effect()
    }
}