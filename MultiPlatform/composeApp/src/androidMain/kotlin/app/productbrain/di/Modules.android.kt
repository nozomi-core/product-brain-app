package app.productbrain.di

import app.productbrain.data.database.AppDatabase
import getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModules = module {
    single<AppDatabase> {
        getDatabaseBuilder(androidContext()).build()
    }
}