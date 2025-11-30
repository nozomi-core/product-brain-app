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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import app.productbrain.data.common.CountryCode
import app.productbrain.data.repository.settings.SettingList
import app.productbrain.data.repository.settings.SettingsRepository
import app.productbrain.design.theme.ProTheme
import app.productbrain.design.theme.ProductTheme
import kotlinx.coroutines.launch
import org.koin.compose.getKoin

@Composable
fun UserOnboardingRoute(onCompleted: () -> Unit) {
    val scope = rememberCoroutineScope()
    val repo = getKoin().get<SettingsRepository>()

    ProTheme {
        Scaffold(
            bottomBar = {
                Box(modifier = Modifier.padding(
                    bottom = ProductTheme.spacing.extraLarge
                )) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            scope.launch {
                                repo.set(SettingList.ONBOARDING_COMPLETE, true)
                                onCompleted()
                            }
                        }
                    ) {
                        Text("Complete")
                    }
                }
            }
        ) { p ->
            Column(modifier = Modifier.padding(p))  {
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