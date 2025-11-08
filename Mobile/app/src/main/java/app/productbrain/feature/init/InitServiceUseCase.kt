package app.productbrain.feature.init

import kotlinx.coroutines.delay

class InitServiceUseCase {
    suspend operator fun invoke(): InitService.State {
        delay(5000)
        return InitService.State.Complete
    }
}