package app.productbrain.di

import app.productbrain.feature.startup.usecase.CompleteOnBoardingUseCase
import app.productbrain.feature.startup.usecase.CreateLocalUserUseCase
import app.productbrain.feature.startup.usecase.IsUserOnBoardedUseCase
import app.productbrain.feature.startup.usecase.GetCurrentLocalUserUseCase
import app.productbrain.feature.startup.usecase.SetCurrentLocalUserUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { CompleteOnBoardingUseCase(get(), get()) }
    factory { CreateLocalUserUseCase(get(), get()) }
    factory { GetCurrentLocalUserUseCase(get()) }
    factory { IsUserOnBoardedUseCase(get()) }
    factory { SetCurrentLocalUserUseCase(get()) }
}