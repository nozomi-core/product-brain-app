package app.productbrain.feature.vendor.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.productbrain.feature.vendor.viewmodel.AddVendorViewModel
import app.productbrain.feature.vendor.viewmodel.AddVendorViewModel.*
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddVendorRoute() {
    val viewModel = koinViewModel<AddVendorViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddVendorScreen(
        state = state,
        send = viewModel::send
    )
}

@Composable
fun AddVendorScreen(
    state: State,
    send: (Action) -> Unit
) {
    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    send(Action.SubmitVendor)
                }
            ) {
                Text("Submit")
            }
        }
    ) { p ->
        Column(modifier = Modifier.padding(p)) {
            OutlinedTextField(
                placeholder = {
                    Text("Vendor name")
                },
                value = state.vendor?.name ?: "",
                onValueChange = {
                    send(Action.UpdateVendorName(it))
                }
            )
        }
    }
}