package app.productbrain

import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.productbrain.feature.NavRoutes
import app.productbrain.feature.main.view.MainApp
import app.productbrain.feature.startup.view.StartupApp
import app.productbrain.feature.startup.view.CreateUserProfileApp
import app.productbrain.feature.startup.view.UserOnboardingApp
import app.productbrain.feature.startup.viewmodel.StartupViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val startupViewModel = koinViewModel<StartupViewModel>()
    val startState by startupViewModel.viewState.collectAsStateWithLifecycle(StartupViewModel.ViewState.Cold)

    when(startState) {
        StartupViewModel.ViewState.Cold -> StartupApp()
        StartupViewModel.ViewState.Ready -> MainApp()
        StartupViewModel.ViewState.UserOnBoarding -> UserOnboardingApp(
            onCompleted = {
                startupViewModel.invalidate()
            }
        )
        StartupViewModel.ViewState.CreateProfile -> CreateUserProfileApp(
            onComplete = {
                startupViewModel.invalidate()
            }
        )
    }
}