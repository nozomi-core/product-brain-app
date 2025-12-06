package app.productbrain.data.di

import app.productbrain.data.database.AppDatabase
import app.productbrain.data.provider.ClockProvider
import app.productbrain.data.provider.TransactionProvider
import app.productbrain.data.model.localuser.LocalUserDao
import app.productbrain.data.model.settings.SettingsDao
import app.productbrain.data.model.settings.SettingsRepository
import app.productbrain.data.model.settings.SettingsRepositoryActual
import app.productbrain.data.model.localuser.LocalUserRepository
import app.productbrain.data.model.localuser.LocalUserRepositoryActual
import org.koin.dsl.module

val dataModule = module {
    //Providers
    single { ClockProvider() }
    single { TransactionProvider(get()) }

    //DAO's
    single<SettingsDao> { get<AppDatabase>().settingsDao() }
    single<LocalUserDao> { get<AppDatabase>().localUserDao()  }

    //Repositories
    factory<SettingsRepository> { SettingsRepositoryActual(get()) }
    factory<LocalUserRepository> { LocalUserRepositoryActual(get()) }
}