package app.productbrain.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.productbrain.ui.theme.ProTheme

@Composable
fun ProScreen(content: @Composable () -> Unit) {
    ProTheme {
        Scaffold { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        }
    }
}