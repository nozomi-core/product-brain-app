package app.productbrain.data.di

import app.productbrain.data.database.AppDatabase
import app.productbrain.data.provider.ClockProvider
import app.productbrain.data.provider.TransactionProvider
import app.productbrain.data.repository.settings.SettingsRepository
import org.koin.dsl.module

val dataModule = module {
    single { ClockProvider() }
    single { TransactionProvider(get()) }
    single { SettingsRepository(get<AppDatabase>().settingsDao()) }
}