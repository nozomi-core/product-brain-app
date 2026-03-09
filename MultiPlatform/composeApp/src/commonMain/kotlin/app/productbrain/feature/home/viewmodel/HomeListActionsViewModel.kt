package app.productbrain.feature.home.viewmodel

import androidx.lifecycle.ViewModel

class HomeListActionsViewModel: ViewModel() {
    val actions = ActionList.entries
}

enum class ActionList(
    val title: String
) {
    ADD_VENDOR("Add Vendor"),
    ADD_PRODUCT_NOTE("Add Product Note"),
    ADD_PRODUCT("Add Product"),
    ADD_UNIT_PRICE("Add Unit Price")
}