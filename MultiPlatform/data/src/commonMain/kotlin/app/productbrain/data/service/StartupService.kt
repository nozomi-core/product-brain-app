package app.productbrain.data.service

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface StartupServiceObserver {
    val startUpState: StateFlow<StartupService.State>
}

class StartupService: StartupServiceObserver {
    private val state = MutableStateFlow<State>(State.Cold)
    override val startUpState: StateFlow<State>
        get() {
            if(state.value == State.Cold) {
                initialise()
            }
            return state.asStateFlow()
        }

    private fun initialise() {
        state.update {
            State.Initialising
        }
        //TODO: Do init step... Fix Global scope, use a supervisor job
        GlobalScope.launch {
            delay(5000)
            state.update {
                State.StartupComplete
            }
        }
    }


    sealed interface State {
        object Cold: State
        object Initialising: State
        object StartupComplete: State
    }
}