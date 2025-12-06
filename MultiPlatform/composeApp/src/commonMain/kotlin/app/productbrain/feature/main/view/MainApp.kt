package app.productbrain.feature.main.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.productbrain.feature.NavRoutes

@Composable
fun MainApp() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = NavRoutes.Main
        ) {
            composable(route = NavRoutes.Main::class) {
                Scaffold { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues)) {
                        Text("Hello there")
                    }
                }
            }

            composable(route = NavRoutes.Next::class) {
                Scaffold { p ->
                    Column(modifier = Modifier.padding(p)) {
                        Text("Next")
                    }
                }
            }
        }
    }
}