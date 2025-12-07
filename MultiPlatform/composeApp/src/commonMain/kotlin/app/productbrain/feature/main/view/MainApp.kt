package app.productbrain.feature.main.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.productbrain.feature.NavRoute
import app.productbrain.feature.Navigator
import app.productbrain.feature.home.view.HomeListActionRoute
import app.productbrain.feature.vendor.view.AddVendorRoute

class MainNavigator(val controller: NavController): Navigator {
    override fun toRoute(route: NavRoute) {
        controller.navigate(route)
    }
}

@Composable
fun MainApp() {
    MaterialTheme {
        val navController = rememberNavController()

        val navigator = remember(key1 = navController) {
            MainNavigator(navController)
        }

        NavHost(
            navController = navController,
            startDestination = NavRoute.HomeListActions
        ) {
            composable(route = NavRoute.HomeListActions::class) {
                HomeListActionRoute(
                    navigator
                )
            }

            composable(route = NavRoute.AddVendorRoute::class) {
                AddVendorRoute()
            }
        }
    }
}