package app.productbrain.navigation

sealed interface RootDestination {
    data object Main: RootDestination
}