package app.productbrain.feature.startup

import androidx.lifecycle.ViewModel
import app.productbrain.data.service.StartupService
import app.productbrain.data.service.StartupServiceObserver
import kotlinx.coroutines.flow.map

class StartupViewModel(
    startupService: StartupServiceObserver
): ViewModel() {
    val viewState = startupService.startUpState.map { state ->
        when(state) {
            is StartupService.State.StartupComplete -> ViewState.Ready
            else -> ViewState.Cold
        }
    }

    sealed class ViewState {
        object Cold: ViewState()
        object Ready: ViewState()
    }
}