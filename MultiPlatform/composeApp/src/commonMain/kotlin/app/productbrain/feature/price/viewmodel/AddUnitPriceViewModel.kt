package app.productbrain.feature.price.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.productbrain.common.BigNumber
import app.productbrain.common.CurrencyAmount
import app.productbrain.common.CurrencyCodeTag
import app.productbrain.common.Forest
import app.productbrain.common.LazyState
import app.productbrain.common.Maybe
import app.productbrain.data.model.productunit.UnitName
import app.productbrain.data.model.productvariant.ProductVariant
import app.productbrain.data.model.productvariant.ProductVariantRepository
import app.productbrain.data.model.settings.SettingItem
import app.productbrain.data.model.settings.SettingsRepository
import app.productbrain.data.model.vendor.VendorList
import app.productbrain.data.model.vendor.VendorRepository
import app.productbrain.design.lang.InputForm
import app.productbrain.feature.product.usecase.MatchProductUseCase
import app.productbrain.feature.vendor.usecase.MatchVendorUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AddUnitPriceViewModel(
    private val matchVendorUseCase: MatchVendorUseCase,
    private val matchProductUseCase: MatchProductUseCase,
    private val settingsRepository: SettingsRepository,
    private val vendorRepository: VendorRepository,
    private val productVariantRepository: ProductVariantRepository
): ViewModel() {

    private val lazyVendor = LazyState {
        vendorRepository.queryAll()
    }

    private val lazyProducts = LazyState {
        productVariantRepository.getAll()
    }

    private val _viewState = MutableStateFlow(
        ViewState(
            vendorList = lazyVendor,
            productList = lazyProducts
        )
    )

    val viewState = _viewState.asStateFlow()

    @OptIn(FlowPreview::class)
    private val productSearch =
        _viewState.map { it.productName }
        .distinctUntilChanged()
        .debounce(200)

    init {
        runBlocking {
            val currencyCode = settingsRepository.get(SettingItem.CurrencyCode)
            _viewState.value = viewState.value.copy(
                unitPrice = InputForm.Invalid("", CurrencyAmount(BigNumber.Zero, currencyCode))
            )
        }

        viewModelScope.launch {
            productSearch.collectLatest { productText ->
                val productList = lazyProducts.value.getOrNull()

                if(productList != null) {
                    val matchedProduct = matchProductUseCase(productText, productList)



                    Forest.d("Product: ${matchedProduct?.name}")
                }
            }
        }
    }

    fun sendAction(action: Action) {
        when(action){
            Action.Submit -> {
                viewModelScope.launch {
                    val vendorList = viewState.value.vendorList.value.getOrNull()
                    val productList = viewState.value.productList.value.getOrNull()

                    val vendorSearch = viewState.value.vendorName
                    val productSearch = viewState.value.productName

                    if(vendorList != null && productList != null) {
                        val matchedVendor = matchVendorUseCase(vendorSearch, vendorList)
                        val matchedProduct = matchProductUseCase(productSearch, productList)

                        Forest.d("Vendor: ${matchedVendor?.name}|Product: ${matchedProduct?.name}|price: ${viewState.value.unitPrice}")
                    }
                }


            }
            is Action.UpdateProductName -> {
                _viewState.update { currentState ->
                    currentState.copy(productName = action.name)
                }
            }
            is Action.UpdateUnitName -> {
                _viewState.update { currentState ->
                    currentState.copy(
                        productUnitRaw = action.name,
                        productUnit = UnitName.of(action.name)
                    )
                }
            }
            is Action.UpdateVendorName -> {
                _viewState.update { currentState ->
                    currentState.copy(vendorName = action.name)
                }
            }

            is Action.UpdateUnitPrice -> {
                _viewState.update { currentState ->
                    currentState.copy(unitPrice = action.amount)
                }
            }
        }
    }

    data class ViewState(
        val productName: String = "",
        val unitPrice: InputForm<CurrencyAmount> = InputForm.Invalid("", CurrencyAmount(BigNumber.Zero, CurrencyCodeTag.AUD)),
        val productUnit: UnitName = UnitName.UNKNOWN,
        val productUnitRaw: String = "",
        val vendorName: String = "",
        val vendorList: LazyState<Maybe<VendorList>>,
        val productList: LazyState<Maybe<List<ProductVariant>>>
    )

    sealed interface Action {
        class UpdateProductName(val name: String): Action
        class UpdateUnitPrice(val amount: InputForm<CurrencyAmount>): Action
        class UpdateUnitName(val name: String): Action
        class UpdateVendorName(val name: String): Action
        object Submit: Action
    }
}