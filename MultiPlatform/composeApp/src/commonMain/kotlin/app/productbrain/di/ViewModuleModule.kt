package app.productbrain.di

import app.productbrain.feature.startup.viewmodel.StartupViewModel
import app.productbrain.feature.startup.viewmodel.UserOnboardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { StartupViewModel(get()) }
    viewModel { UserOnboardingViewModel(get(), get()) }
}