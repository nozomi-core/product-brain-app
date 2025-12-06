package app.productbrain.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TestApp(onCompleted: () -> Unit) {
    Scaffold { p ->
        Column(modifier = Modifier.padding(p)) {
            Text("Hello there")

            Button(
                onClick = onCompleted
            ) {
                Text("Finish test")
            }
        }
    }
}