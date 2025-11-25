package app.productbrain.di


import app.productbrain.feature.startup.StartupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {
    viewModel { StartupViewModel(get()) }
}