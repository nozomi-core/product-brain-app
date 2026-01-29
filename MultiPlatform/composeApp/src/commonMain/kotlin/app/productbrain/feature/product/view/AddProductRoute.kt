package app.productbrain.feature.product.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import app.productbrain.feature.product.viewmodel.AddProductViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddProductRoute() {
    val viewModel: AddProductViewModel = koinViewModel()
    val viewState by viewModel.state.collectAsState()
    AddProductScreen(viewState, viewModel::action)
}

@Composable
private fun AddProductScreen(
    viewState: AddProductViewModel.ViewState,
    action: (AddProductViewModel.Action) -> Unit
) {
    Scaffold { p ->
        Column(modifier = Modifier.padding(p)) {
            OutlinedTextField(
                placeholder = {
                    Text("Product Name")
                },
                modifier = Modifier.fillMaxWidth(),
                value = viewState.productName,
                onValueChange = {
                    action(AddProductViewModel.Action.UpdateProductName(it))
                }
            )

            OutlinedTextField(
                placeholder = {
                    Text("Product Unit")
                },
                modifier = Modifier.fillMaxWidth(),
                value = viewState.productUnitRaw,
                onValueChange = {
                    action(AddProductViewModel.Action.UpdateUnitName(it))
                }
            )

            Button(
                onClick = {
                    action(AddProductViewModel.Action.Submit)
                }
            ) {
                Text("Submit")
            }
        }
    }
}