package app.productbrain.feature.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.productbrain.feature.NavRoute
import app.productbrain.feature.Navigator
import app.productbrain.feature.home.viewmodel.ActionList
import app.productbrain.feature.home.viewmodel.HomeListActionsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeListActionRoute(
    navigator: Navigator
) {
    val viewModel = koinViewModel<HomeListActionsViewModel>()

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(horizontal = 16.dp)) {
            viewModel.actions.forEach { entry ->
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        when(entry) {
                            ActionList.ADD_VENDOR -> navigator.toRoute(NavRoute.AddVendorRoute)
                            ActionList.ADD_PRODUCT_NOTE -> navigator.toRoute(NavRoute.AddProductNote)
                            ActionList.ADD_PRODUCT -> navigator.toRoute(NavRoute.AddProduct)
                        }
                     }
                ) {
                    Text(entry.title)
                }
            }
        }
    }
}