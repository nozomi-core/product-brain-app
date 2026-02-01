package app.productbrain.test

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.productbrain.test.view.SharedViewModelTest

@Composable
fun TestApp(onCompleted: () -> Unit) {
    MaterialTheme {
        Scaffold(
            bottomBar = {
                BottomAppBar {
                    Button(
                        onClick = {
                            onCompleted()
                        }
                    ) {
                        Text("Finish Test")
                    }
                }
            }
        ) { p ->
            Box(modifier = Modifier.padding(p)) {
                SharedViewModelTest()
            }
        }
    }
}


