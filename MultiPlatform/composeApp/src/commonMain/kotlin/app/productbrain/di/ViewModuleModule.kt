package app.productbrain.di

import app.productbrain.feature.home.viewmodel.HomeListActionsViewModel
import app.productbrain.feature.startup.viewmodel.StartupViewModel
import app.productbrain.feature.startup.viewmodel.UserOnboardingViewModel
import app.productbrain.feature.vendor.viewmodel.AddVendorViewModel
import app.productbrain.test.view.SharedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { StartupViewModel(get()) }
    viewModel { UserOnboardingViewModel(get(), get()) }
    viewModel { AddVendorViewModel(get()) }
    viewModel { HomeListActionsViewModel() }

    //Test
    viewModel { SharedViewModel() }
}