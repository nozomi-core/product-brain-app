package app.productbrain.feature.init

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InitService: KoinComponent {
    private val _state = MutableStateFlow<State?>(null)
    val state = _state.asSharedFlow()
    private val initUseCase by inject<InitServiceUseCase>()

    suspend fun create() {
        if(_state.value == null) {
            _state.emit(State.Loading)
            loadInitService()
        }
    }

    sealed interface State {
        object Loading: State
        object Complete: State
        object Error: State
    }

    private suspend  fun loadInitService() {
        try {
            val result = initUseCase()
            _state.emit(result)
        } catch (e: Exception) {
            //TODO: Log exception
            _state.emit(State.Error)
        }
    }
}