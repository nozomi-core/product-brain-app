package app.productbrain.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.productbrain.feature.NavRoute
import app.productbrain.feature.Navigator

@Composable
fun HomeRoute(
    args: NavRoute.Home,
    navigator: Navigator
) {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("Hello there: ${args.id}")

            Button(
                onClick =  {
                    navigator.toRoute(NavRoute.Next)
                }
            ) {
                Text("To Next")
            }
        }
    }
}