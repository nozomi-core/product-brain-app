package app.phoneixshell.quasar.ui.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LocalEffect(action: suspend() -> Unit ) {
    val localCoroutine = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        localCoroutine.launch {
            action()
        }
    }
}