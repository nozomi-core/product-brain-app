package app.productbrain

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.productbrain.data.lang.Forest
import app.productbrain.data.lang.Numbers
import app.productbrain.data.lang.numberOf
import app.productbrain.data.repository.login.LoginRepository
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.Koin
import org.koin.mp.KoinPlatform.getKoin

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        doTest()

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
fun doTest() {
    val repo: LoginRepository = getKoin().get()

    LaunchedEffect(Unit) {

        val num1 = numberOf("15.33").getOrDefault(Numbers.Zero)
        val num2 = numberOf("90.77").getOrDefault(Numbers.Zero)

        val result = num1 + num2

        Forest.d("Summation: $result")
        repo.insertLogin().onSuccess { entity ->

        }
    }
}