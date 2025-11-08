package app.productbrain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.productbrain.feature.init.SplashRoute
import app.productbrain.navigation.RootNavigator
import app.productbrain.navigation.RootDestination

enum class ProDestinations(val route: String) {
    MAIN("main"),
    SPLASH("splash")
    ;
}

class ProActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navigator = LocalNavigator(navController)

            NavHost(
                navController = navController,
                startDestination = ProDestinations.SPLASH.route
            ) {

                composable(route = ProDestinations.SPLASH.route) {
                    SplashRoute(
                        onComplete = {

                        }
                    )
                }

                composable(route = "main") {
                    Text("Main")
                }
            }
        }
    }
}

class LocalNavigator(private val navController: NavController): RootNavigator {
    override fun goto(route: RootDestination) {
        when(route) {
            is RootDestination.Main -> navController.navigate("main")
        }
    }

}