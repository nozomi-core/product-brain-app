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
import app.productbrain.data.repository.settings.Settings
import app.productbrain.data.repository.settings.SettingsRepository
import app.productbrain.feature.startup.StartupViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val startupViewModel = koinViewModel<StartupViewModel>()
    val startState by startupViewModel.viewState.collectAsStateWithLifecycle(StartupViewModel.ViewState.Cold)

    when(startState) {
        is StartupViewModel.ViewState.Cold -> ColdApp()
        is StartupViewModel.ViewState.Ready -> ReadyApp()
    }
}

@Composable
fun ColdApp() {
    val db = getKoin().get<SettingsRepository>()

    LaunchedEffect(Unit) {

        val pop = db.get(Settings.DARK_MODE)

        db.set(Settings.USERNAME, pop::class.qualifiedName!!)

    }


    Scaffold { p ->
        Column(modifier = Modifier.padding(p)) {
            Text("Loading...")
        }
    }
}

@Composable
fun ReadyApp() {
    MaterialTheme {
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