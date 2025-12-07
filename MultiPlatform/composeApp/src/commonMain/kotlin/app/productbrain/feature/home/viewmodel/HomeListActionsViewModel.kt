package app.productbrain.feature.home.viewmodel

import androidx.lifecycle.ViewModel

class HomeListActionsViewModel: ViewModel() {
    val actions = ActionList.entries
}

enum class ActionList(
    val title: String
) {
    ADD_VENDOR("Add Vendor")
}