package app.productbrain.test.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import app.productbrain.common.CurrencyAmount
import app.productbrain.common.CurrencyCodeTag
import app.productbrain.common.BigNumber
import app.productbrain.design.compose.ProCurrency
import app.productbrain.design.lang.InputForm
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

class SharedViewModel: ViewModel() {
    var initState = "NA"
}

@Composable
fun SharedViewModelTest() {
    Box() {

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "number"
        ) {
            composable(route = "number") {
                var curr: InputForm<CurrencyAmount> by remember {
                    mutableStateOf(
                        InputForm.Valid(
                            "0", CurrencyAmount(
                                BigNumber.Zero,
                                CurrencyCodeTag.AUD
                            )
                        )
                    )
                }

                ProCurrency(
                    value = curr,
                    onValue = {
                        curr = it
                    }
                )
            }

            navigation(
                route = "main_graph",
                startDestination = "send"
            ) {
                composable(route = "send") { entry ->
                    val parentEntry = remember(navController) {
                        navController.getBackStackEntry("main_graph")
                    }

                    val sharedView = koinViewModel<SharedViewModel>(viewModelStoreOwner = parentEntry)
                    ScreenA(sharedView) {
                        navController.navigate("next")
                    }
                }

                composable(route = "next") {
                    val parentEntry = remember(navController) {
                        navController.getBackStackEntry("main_graph")
                    }

                    val sharedView = koinViewModel<SharedViewModel>(viewModelStoreOwner = parentEntry)
                    ScreenB(sharedView) {

                    }
                }
            }
        }

    }
}

@Composable
fun ScreenA(
    sharedViewModel: SharedViewModel = koinViewModel(),
    onCompleted: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(1000)
        sharedViewModel.initState = "ScreenA"
        onCompleted()
    }
}

@Composable
fun ScreenB(
    sharedViewModel: SharedViewModel = koinViewModel(),
    onCompleted: () -> Unit
) {
    Scaffold { p ->
        Column(modifier = Modifier.padding(p)) {
            Text(sharedViewModel.initState)
        }
    }
}