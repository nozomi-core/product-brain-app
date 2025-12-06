package app.productbrain.feature

import kotlinx.serialization.Serializable

sealed class NavRoutes {
    @Serializable
    object Main: NavRoutes()
    @Serializable
    object Next: NavRoutes()
}