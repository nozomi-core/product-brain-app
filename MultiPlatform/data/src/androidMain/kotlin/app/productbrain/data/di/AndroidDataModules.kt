package app.productbrain.data.di

import app.productbrain.common.AndroidPlatformAssets
import app.productbrain.common.PlatformAssets
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidDataModules = module {
    single<PlatformAssets> { AndroidPlatformAssets(androidContext()) }
}