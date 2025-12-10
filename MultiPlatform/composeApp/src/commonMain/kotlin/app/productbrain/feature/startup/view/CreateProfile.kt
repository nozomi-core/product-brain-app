package app.productbrain.feature.startup.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import app.productbrain.common.unwrap
import app.productbrain.feature.startup.usecase.CreateLocalUserUseCase
import app.productbrain.feature.startup.usecase.SetCurrentLocalUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.compose.getKoin

@Composable
fun CreateUserProfileApp(onCompleted: () -> Unit) {
    val createProfileUseCase = getKoin().get<CreateLocalUserUseCase>()
    val setCurrentUser = getKoin().get<SetCurrentLocalUserUseCase>()

    LaunchedEffect(Unit) {


        withContext(Dispatchers.IO) {
            createProfileUseCase()
                .then(setCurrentUser::invoke)
                .unwrap { it }
        }.onSuccess { result ->
            if (result is SetCurrentLocalUserUseCase.Result.Ok) {
                onCompleted()
            } else {
                //TODO: Handle fail case
            }
        }
    }


    Text("Create profile")
}