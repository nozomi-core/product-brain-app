package app.productbrain.design.compose

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import app.productbrain.data.common.CurrencyAmount
import app.productbrain.data.lang.BigNumber
import app.productbrain.data.lang.Maybe
import app.productbrain.design.lang.InputForm

@Composable
fun ProCurrency(
    value: InputForm<CurrencyAmount>,
    onValue: (InputForm<CurrencyAmount>) -> Unit
) {
    OutlinedTextField(
        value = value.text,
        label = {
            Text("Money")
        },
        placeholder = {
            Text("Amount")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Decimal
        ),
        onValueChange = { rawValue ->
            val result = BigNumber.of(rawValue)

            when(result) {
                is Maybe.Value -> onValue(
                    InputForm.Valid(
                        text = rawValue,
                    CurrencyAmount(result.value, value.lastValue.code)
                    )
                )
                is Maybe.Error -> onValue(InputForm.Invalid(rawValue, value.lastValue))
            }
        }
    )
}