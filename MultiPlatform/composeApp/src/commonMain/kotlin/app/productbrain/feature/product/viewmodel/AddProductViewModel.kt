package app.productbrain.feature.product.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.productbrain.data.model.productunit.UnitName
import app.productbrain.design.lang.InputForm
import app.productbrain.design.lang.InputForm.*
import app.productbrain.feature.product.usecase.AddProductUseCase
import kotlinx.coroutines.launch

class AddProductViewModel(
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow(ViewState())
    val state: StateFlow<ViewState> = _viewState.asStateFlow()

    fun action(action: Action) {
        when(action) {
            is Action.UpdateProductName -> {
                _viewState.value = _viewState.value.copy(
                    productName = action.value
                )
            }

            is Action.UpdateUnitName -> {
                val findUnit = UnitName.values().find { it.key == action.value }

                _viewState.value = _viewState.value.copy(
                    productUnitRaw = action.value,
                    productUnit = findUnit?.let { Valid(action.value, it) } ?: Invalid(action.value,
                        DEFAULT_UNIT)
                )
            }

            Action.Submit -> {
                viewModelScope.launch {
                    addProductUseCase(
                        name = _viewState.value.productName,
                        unit =  _viewState.value.productUnit.getOrLast()
                    )
                }
            }
        }
    }

    data class ViewState(
        val productName: String = "",
        val productUnitRaw: String = "",
        val productUnit: InputForm<UnitName> = InputForm.Invalid("", DEFAULT_UNIT)
    )

    sealed interface Action {
        class UpdateProductName(val value: String): Action
        class UpdateUnitName(val value: String): Action
        object Submit: Action
    }

    companion object {
        val DEFAULT_UNIT = UnitName.QTY
    }
}