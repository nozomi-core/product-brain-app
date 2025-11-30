package app.productbrain.feature.startup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.productbrain.data.common.CountryCodeTag
import app.productbrain.data.common.CurrencyCodeTag
import app.productbrain.data.repository.settings.SettingItem
import app.productbrain.data.repository.settings.SettingsRepository
import app.productbrain.feature.startup.usecase.CompleteOnBoardingUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserOnboardingViewModel(
    private val completeOnBoardingUseCase: CompleteOnBoardingUseCase,
    private val settingsRepository: SettingsRepository
): ViewModel() {
    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()

    val viewState: Flow<ViewState> = settingsRepository.requireSettingKeySet(
        listOf(SettingItem.CountryCode, SettingItem.CountryCode)
    ).map {
        if(it) {
            ViewState.OnBoardingDone
        } else {
            ViewState.OnBoardingNotCompleted
        }
    }

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
            settingsRepository.set(SettingItem.CurrencyCode, CurrencyCodeTag.AUD)
            settingsRepository.set(SettingItem.CountryCode, CountryCodeTag.AU)
        }
    }

    sealed interface ViewState {
        object Cold: ViewState
        object OnBoardingNotCompleted: ViewState
        object OnBoardingDone: ViewState
    }


    sealed class Action {
        object FinishOnboarding: Action()
        object SetDefault: Action()
    }

    sealed class Effect {
        object OnBoardingCompleted: Effect()
    }
}