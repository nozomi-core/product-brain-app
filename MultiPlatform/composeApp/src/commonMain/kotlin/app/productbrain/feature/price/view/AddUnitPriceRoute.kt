package app.productbrain.feature.price.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.productbrain.design.compose.ProCurrency
import app.productbrain.feature.price.viewmodel.AddUnitPriceViewModel
import app.productbrain.feature.product.viewmodel.AddProductViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddUnitPriceRoute(
    viewModel: AddUnitPriceViewModel = koinViewModel(),
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    AddUnitPriceScreen(viewState, viewModel::sendAction)
}

@Composable
fun AddUnitPriceScreen(
    viewState: AddUnitPriceViewModel.ViewState,
    sendAction: (AddUnitPriceViewModel.Action) -> Unit
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
                    sendAction(AddUnitPriceViewModel.Action.UpdateProductName(it))
                }
            )
            ProCurrency(
                value = viewState.unitPrice,
                onValue = { value ->
                    sendAction(AddUnitPriceViewModel.Action.UpdateUnitPrice(value))
                }
            )

            OutlinedTextField(
                placeholder = {
                    Text("Unit Name")
                },
                modifier = Modifier.fillMaxWidth(),
                value = viewState.productUnitRaw,
                onValueChange = {
                    sendAction(AddUnitPriceViewModel.Action.UpdateUnitName(it))
                }
            )

            OutlinedTextField(
                placeholder = {
                    Text("Vendor")
                },
                modifier = Modifier.fillMaxWidth(),
                value = viewState.vendorName,
                onValueChange = {
                    sendAction(AddUnitPriceViewModel.Action.UpdateVendorName(it))
                }
            )

            Button(
                onClick = {
                    sendAction(AddUnitPriceViewModel.Action.Submit)
                }
            ) {
                Text("Submit")
            }
        }
    }
}