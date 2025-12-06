package app.productbrain.feature.startup.viewmodel

import androidx.lifecycle.ViewModel
import app.productbrain.feature.startup.service.StartupService
import app.productbrain.feature.startup.service.StartupServiceObserver
import app.productbrain.feature.startup.usecase.IsUserOnBoardedUseCase
import app.productbrain.feature.startup.usecase.GetCurrentLocalUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
* StartupViewModel is responsible for loading the initial global state
* and getting the database to a state where the user can start creating data
*/
class StartupViewModel(
    val startupService: StartupServiceObserver
): ViewModel() {
    //This Flow is the root startup state
    val viewState: Flow<ViewState> = startupService.startUpState.map { state ->

        /*
        Startup state = Onboarding -> CreateProfile -> Ready
        */
        when(state) {
            is StartupService.State.StartupComplete -> {
                if (state.isOnboarded is IsUserOnBoardedUseCase.Result.OnBoardingRequired) {
                    ViewState.UserOnBoarding
                } else if(state.currentUser is GetCurrentLocalUserUseCase.Result.UserNotFound)
                    ViewState.CreateProfile
                else {
                    ViewState.Ready
                }
            }
            else -> ViewState.Cold
        }
    }

    fun invalidate() = startupService.invalidate()

    sealed interface ViewState {
        object Cold: ViewState
        object Ready: ViewState
        object UserOnBoarding: ViewState
        object CreateProfile: ViewState
    }
}