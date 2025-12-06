package app.productbrain.feature.startup.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StartupApp() {
    Scaffold { p ->
        Column(modifier = Modifier.padding(p)) {
            Text("Loading...")
        }
    }
}