package app.productbrain.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.productbrain.feature.NavRoute
import app.productbrain.feature.Navigator

@Composable
fun NextRoute(
    args: NavRoute.Next,
    navigator: Navigator
) {
    Scaffold { p ->
        Column(modifier = Modifier.padding(p)) {
            Text("Next")
        }
    }
}