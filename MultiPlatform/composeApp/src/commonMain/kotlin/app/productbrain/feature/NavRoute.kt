package app.productbrain.feature

import kotlinx.serialization.Serializable

sealed class NavRoute {
    @Serializable
    object HomeListActions: NavRoute()
    @Serializable
    object AddVendorRoute: NavRoute()
    @Serializable
    object AddProductNote: NavRoute()
}