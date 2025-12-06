package app.productbrain

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.productbrain.feature.startup.usecase.CreateLocalUserUseCase
import app.productbrain.feature.startup.usecase.SetCurrentLocalUserUseCase
import app.productbrain.feature.startup.view.UserOnboardingRoute
import app.productbrain.feature.startup.viewmodel.StartupViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import productbrain.composeapp.generated.resources.Res
import productbrain.composeapp.generated.resources.button_title

@Composable
@Preview
fun App() {
    val startupViewModel = koinViewModel<StartupViewModel>()
    val startState by startupViewModel.viewState.collectAsStateWithLifecycle(StartupViewModel.ViewState.Cold)

    when(startState) {
        StartupViewModel.ViewState.Cold -> ColdApp()
        StartupViewModel.ViewState.Ready -> ReadyApp()
        StartupViewModel.ViewState.UserOnBoarding -> UserOnboardingRoute(
            onCompleted = {
                startupViewModel.invalidate()
            }
        )
        StartupViewModel.ViewState.CreateProfile -> CreateUserProfileRoute(
            onComplete = {
                startupViewModel.invalidate()
            }
        )
    }
}

@Composable
fun ColdApp() {
    Scaffold { p ->
        Column(modifier = Modifier.padding(p)) {
            Text("Loading...")
        }
    }
}

@Composable
fun ReadyApp() {
    MaterialTheme {
        stringResource(Res.string.button_title)

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "main"
        ) {
            composable(route = "main") {
                Scaffold { paddingValues ->
                    Column(modifier = Modifier.padding(paddingValues)) {
                        Text("Hello there")
                    }
                }
            }
        }
    }
}

@Composable
fun CreateUserProfileRoute(onComplete: () -> Unit) {
    val createProfileUseCase = getKoin().get<CreateLocalUserUseCase>()
    val setCurrentUser = getKoin().get<SetCurrentLocalUserUseCase>()

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            createProfileUseCase().then { localUser ->
                setCurrentUser(localUser).getOrNull()
            }.onSuccess { ok ->
                if(ok is SetCurrentLocalUserUseCase.Result.Ok) {
                    onComplete()
                } else {
                    //TODO: Handle fail case
                }
            }
        }
    }

    Text("Create profile")
}