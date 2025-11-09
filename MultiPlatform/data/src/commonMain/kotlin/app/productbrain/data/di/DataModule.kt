package app.productbrain.data.di

import app.productbrain.data.MultiService
import org.koin.dsl.module

val dataModule = module {
    single { MultiService("hello there") }
}