package app.productbrain.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProMoneyInput() {
    Text("Monies")
}

@Preview
@Composable
fun PreviewMoney() {
    ProScreen {
        ProMoneyInput()
    }
}