package app.productbrain.feature.product.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.productbrain.common.Forest
import app.productbrain.data.model.fuzzytextsearch.FuzzyTextSearchEngine
import app.productbrain.data.model.vendor.RemoteVendor
import app.productbrain.data.model.vendor.VendorList
import app.productbrain.data.model.vendor.VendorRepository
import app.productbrain.extensions.normalise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddProductNoteViewModel(
    private val vendorRepository: VendorRepository,
    private val fuzzyTextSearchEngine: FuzzyTextSearchEngine
): ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(ViewState())
    val state = _viewState.asStateFlow()

    data class ViewState(
        val vendorName: String = "",
        val note: String = ""
    )

    fun updateVendor(name: String) {
        _viewState.update {
            it.copy(
                vendorName = name.normalise()
            )
        }
    }

    fun updateNote(note: String) {
        _viewState.update {
            it.copy(
                note = note.normalise()
            )
        }
    }

    fun submit() {
        viewModelScope.launch {
            vendorRepository.queryAll()
                .then(::fuzzySearchVendors)
                .then(::submitWithVendor)
        }
    }

    fun submitWithVendor(vendor: RemoteVendor?) {
        if(vendor != null ) {
            Forest.d("FoundVendor: ${vendor.id.value}|${vendor.name}")
        } else {
            Forest.d("Not Found")
        }
    }

    suspend fun fuzzySearchVendors(vendors: VendorList): RemoteVendor? {
        val search = _viewState.value.vendorName

        return fuzzyTextSearchEngine.prepare(vendors.vendors, ::vendorSearchMatcher)
            .search(search)
            .firstOrNull { it.isAutoMatchQualified }?.matched
    }

    fun vendorSearchMatcher(vendor: RemoteVendor) = buildList {
        add(vendor.name)
        addAll(vendor.alias)
    }
}

