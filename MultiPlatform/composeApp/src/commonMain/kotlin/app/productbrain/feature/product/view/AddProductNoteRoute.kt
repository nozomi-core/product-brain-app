package app.productbrain.feature.product.view

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
import app.productbrain.feature.product.viewmodel.AddProductNoteViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddProductNoteRoute() {
    val viewModel = koinViewModel<AddProductNoteViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { p ->
        Column(modifier = Modifier.padding(p)) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Vendor")
                },
                value = state.vendorName,
                onValueChange = {
                    viewModel.updateVendor(it)
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Note")
                },
                value = state.note,
                onValueChange = {
                    viewModel.updateNote(it)
                }
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.submit()
                }
            ) {
                Text("Submit Note")
            }
        }
    }
}