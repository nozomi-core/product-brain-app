package app.productbrain.feature.init

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashViewModel(): ViewModel(), KoinComponent {
    private val initService by inject<InitService>()

    private val _state = MutableStateFlow(State(
        isLoading = false,
        isComplete = false
    ))
    val state = _state.asSharedFlow()

    init {
        viewModelScope.launch {
            initService.state.collect { data ->
                val isLoading: Boolean = when(data) {
                    is InitService.State.Complete -> false
                    is InitService.State.Loading -> true
                    is InitService.State.Error -> false
                    null -> true
                }

                val isCompleted: Boolean = when(data) {
                    is InitService.State.Complete -> true
                    else -> false
                }
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = isLoading,
                        isComplete = isCompleted
                    )
                }
            }
        }
    }

    data class State(
        val isLoading: Boolean,
        val isComplete: Boolean
    )
}