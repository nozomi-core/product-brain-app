package app.productbrain.di

import app.productbrain.feature.init.InitService
import app.productbrain.feature.init.InitServiceUseCase
import org.koin.dsl.module

val applicationModules = module {
    single<InitService> { InitService() }
    single<InitServiceUseCase> { InitServiceUseCase() }
}