package app.productbrain.feature.startup.service

import app.productbrain.feature.startup.usecase.IsUserOnBoardedUseCase
import app.productbrain.feature.startup.usecase.GetCurrentLocalUserUseCase
import app.productbrain.feature.startup.usecase.StartUpUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface StartupServiceObserver {
    val startUpState: StateFlow<StartupService.State>
    fun invalidate()
}

class StartupService(
    private val startupUseCase: StartUpUseCase
): StartupServiceObserver {
    private val state = MutableStateFlow<State>(State.Cold)
    override val startUpState: StateFlow<State>
        get() {
            if (state.value == State.Cold) {
                initialise()
            }
            return state.asStateFlow()
        }

    override fun invalidate() {
        loadState()
    }

    private  fun initialise() {
        state.update {
            State.Initialising
        }
        loadState()
    }

    private fun loadState() {
        //TODO: Do init step... Fix Global scope, use a supervisor job
        GlobalScope.launch {
            state.update {
                startupUseCase()
            }
        }
    }

    sealed interface State {
        object Cold : State
        object Initialising : State
        data class StartupComplete(
            val isOnboarded: IsUserOnBoardedUseCase.Result,
            val currentUser: GetCurrentLocalUserUseCase.Result
        ) : State
    }
}