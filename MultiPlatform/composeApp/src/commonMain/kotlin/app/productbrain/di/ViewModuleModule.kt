package app.productbrain.di

import app.productbrain.feature.home.viewmodel.HomeListActionsViewModel
import app.productbrain.feature.price.viewmodel.AddUnitPriceViewModel
import app.productbrain.feature.product.viewmodel.AddProductNoteViewModel
import app.productbrain.feature.product.viewmodel.AddProductViewModel
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
    viewModel { AddProductNoteViewModel(get(), get()) }
    viewModel { AddProductViewModel(get()) }

    //Test
    viewModel { SharedViewModel() }
    viewModel { AddUnitPriceViewModel(get(), get(), get(), get(), get()) }
}