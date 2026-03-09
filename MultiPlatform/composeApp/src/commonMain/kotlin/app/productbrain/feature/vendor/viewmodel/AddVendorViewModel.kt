package app.productbrain.feature.vendor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.productbrain.common.RemoteId
import app.productbrain.data.model.vendor.Vendor
import app.productbrain.data.model.vendor.VendorLocalId
import app.productbrain.data.model.vendor.VendorRepository
import app.productbrain.extensions.normalise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddVendorViewModel(
    private val vendorRepository: VendorRepository
): ViewModel() {
    private val _state = MutableStateFlow<ViewState>(ViewState())
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
                            name = action.value.normalise()
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
            localId = VendorLocalId.create(),
            remoteId = RemoteId.NoBinding,
            name = "",
            alias = listOf(
                "MyAlias"
            )
        )
    }

    data class ViewState(
        val vendor: Vendor? = null
    )

    sealed interface Action {
        class UpdateVendorName(val value: String): Action
        object SubmitVendor: Action
    }
}