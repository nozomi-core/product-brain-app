package app.productbrain.di

import app.productbrain.feature.startup.service.StartupService
import app.productbrain.feature.startup.service.StartupServiceObserver
import org.koin.dsl.module

val serviceModule = module {
    single<StartupServiceObserver> { StartupService(get()) }
}