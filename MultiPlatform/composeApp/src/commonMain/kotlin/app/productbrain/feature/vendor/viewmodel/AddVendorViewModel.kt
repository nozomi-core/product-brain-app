package app.productbrain.feature.vendor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.productbrain.data.model.vendor.Vendor
import app.productbrain.data.model.vendor.VendorId
import app.productbrain.data.model.vendor.VendorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddVendorViewModel(
    private val vendorRepository: VendorRepository
): ViewModel() {
    private val _state = MutableStateFlow<State>(State())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                vendor = createEmptyVendor()
            )
        }
    }

    fun send(action: Action) {
        when(action) {
            is Action.UpdateVendorName -> {
                _state.update { state ->
                    state.copy(
                        vendor = state.vendor?.copy(
                            name = action.value
                        )
                    )
                }
            }

            Action.SubmitVendor -> {
                viewModelScope.launch {
                    _state.value.vendor?.let {
                        vendorRepository.upsert(it)
                        clearForm()
                    }
                }
            }
        }
    }

    private fun clearForm() {
        _state.update {
            it.copy(
                vendor = createEmptyVendor()
            )
        }
    }

    private fun createEmptyVendor(): Vendor {
        return Vendor(
            id = VendorId.create(),
            name = ""
        )
    }

    data class State(
        val vendor: Vendor? = null
    )

    sealed interface Action {
        class UpdateVendorName(val value: String): Action
        object SubmitVendor: Action
    }
}