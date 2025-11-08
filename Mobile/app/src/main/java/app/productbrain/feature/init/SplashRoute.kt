package app.productbrain.feature.init

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.productbrain.ui.view.ProScreen

/* SplashScreen is used to load some initial global data from application level before proceeding on */
@Composable
fun SplashRoute(
    onComplete: () -> Unit,
    viewModel: SplashViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle(null)

    ProScreen {
        Column {
            state?.let { current ->
                Text("SplashScreen")

                if(current.isLoading) {
                    CircularProgressIndicator()
                }

                if(current.isComplete) {
                  onComplete()
                }
            }
        }
    }
}