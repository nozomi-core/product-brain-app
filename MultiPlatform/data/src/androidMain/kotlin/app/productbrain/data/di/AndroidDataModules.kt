package app.productbrain.data.di

import app.productbrain.data.lang.AndroidPlatformAssets
import app.productbrain.data.lang.PlatformAssets
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidDataModules = module {
    single<PlatformAssets> { AndroidPlatformAssets(androidContext()) }
}