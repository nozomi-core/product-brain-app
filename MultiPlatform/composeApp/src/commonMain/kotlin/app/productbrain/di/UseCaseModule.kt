package app.productbrain.di

import app.productbrain.feature.startup.usecase.DoesUserNeedOnboardingUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { DoesUserNeedOnboardingUseCase(get()) }
}