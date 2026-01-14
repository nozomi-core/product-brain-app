package app.productbrain.di

import app.productbrain.feature.startup.usecase.CompleteOnBoardingUseCase
import app.productbrain.feature.startup.usecase.CreateLocalUserUseCase
import app.productbrain.feature.startup.usecase.IsUserOnBoardedUseCase
import app.productbrain.feature.startup.usecase.GetCurrentLocalUserUseCase
import app.productbrain.feature.startup.usecase.InitDatabaseUseCase
import app.productbrain.feature.startup.usecase.SetCurrentLocalUserUseCase
import app.productbrain.feature.startup.usecase.StartUpUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { CompleteOnBoardingUseCase(get(), get()) }
    factory { CreateLocalUserUseCase(get(), get()) }
    factory { GetCurrentLocalUserUseCase(get()) }
    factory { IsUserOnBoardedUseCase(get()) }
    factory { SetCurrentLocalUserUseCase(get()) }
    factory { StartUpUseCase(get(), get(), get()) }
    factory { InitDatabaseUseCase(get(), get(), get()) }
}