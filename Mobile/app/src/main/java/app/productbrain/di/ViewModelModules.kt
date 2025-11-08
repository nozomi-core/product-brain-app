package app.productbrain.di

import app.productbrain.feature.init.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { SplashViewModel() }
}