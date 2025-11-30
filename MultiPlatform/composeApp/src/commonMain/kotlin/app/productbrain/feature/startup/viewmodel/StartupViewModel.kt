package app.productbrain.feature.startup.viewmodel

import androidx.lifecycle.ViewModel
import app.productbrain.feature.startup.service.StartupService
import app.productbrain.feature.startup.service.StartupServiceObserver
import app.productbrain.feature.startup.usecase.DoesUserNeedOnboardingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import productbrain.composeapp.generated.resources.Res

class StartupViewModel(
    val startupService: StartupServiceObserver
): ViewModel() {
    //This Flow is the root startup state
    val viewState: Flow<ViewState> = startupService.startUpState.map { state ->
        when(state) {
            is StartupService.State.StartupComplete -> {
                if (state.isOnboarded is DoesUserNeedOnboardingUseCase.Result.OnBoardingRequired) {
                    ViewState.UserOnBoarding
                } else {
                    ViewState.Ready
                }
            }
            else -> ViewState.Cold
        }
    }

    fun invalidate() = startupService.invalidate()

    sealed class ViewState {
        object Cold: ViewState()
        object Ready: ViewState()
        object UserOnBoarding: ViewState()
    }
}