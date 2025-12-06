package app.productbrain.feature

import kotlinx.serialization.Serializable

sealed class NavRoute {
    @Serializable
    class Home(val id: String): NavRoute()
    @Serializable
    object Next: NavRoute()
}