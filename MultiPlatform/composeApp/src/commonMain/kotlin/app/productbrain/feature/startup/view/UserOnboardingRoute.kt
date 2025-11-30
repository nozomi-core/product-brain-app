package app.productbrain.feature.startup.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.productbrain.data.common.CountryCode
import app.productbrain.design.effect.ObserveEffect
import app.productbrain.design.theme.ProTheme
import app.productbrain.design.theme.ProductTheme
import app.productbrain.feature.startup.viewmodel.UserOnboardingViewModel
import app.productbrain.feature.startup.viewmodel.UserOnboardingViewModel.*
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserOnboardingRoute(onCompleted: () -> Unit) {
    val viewModel = koinViewModel<UserOnboardingViewModel>()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle(ViewState.Cold)

    ObserveEffect(viewModel.effect) { effect ->
        when (effect) {
            is Effect.OnBoardingCompleted -> onCompleted()
        }
    }

    UserOnBoardingScreen(
        viewState = viewState,
        send = viewModel::sendAction
    )
}

@Composable
fun UserOnBoardingScreen(
    viewState: ViewState,
    send: (Action) -> Unit
) {
    ProTheme {
        Scaffold(
            bottomBar = {
                Box(
                    modifier = Modifier.padding(
                        bottom = ProductTheme.spacing.extraLarge
                    )
                ) {
                    Button(
                        enabled = viewState is ViewState.OnBoardingDone,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            send(Action.FinishOnboarding)
                        }
                    ) {
                        Text("Complete")
                    }
                }
            }
        ) { p ->
            Column(modifier = Modifier.padding(p)) {

                Button(
                    onClick = {
                        send(Action.SetDefault)
                    }
                ) {
                    Text("Set default")
                }

                Column {
                    Text("UserOnBoarding")

                    var expanded by remember { mutableStateOf(true) }

                    DropdownMenu(
                        modifier = Modifier.fillMaxWidth(),
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        CountryCode.entries.forEach { code ->
                            Text(code.displayName)
                        }
                    }
                }
            }
        }
    }
}