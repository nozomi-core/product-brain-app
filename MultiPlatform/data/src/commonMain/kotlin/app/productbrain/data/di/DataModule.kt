package app.productbrain.data.di

import app.productbrain.data.database.AppDatabase
import app.productbrain.data.provider.ClockProvider
import app.productbrain.data.provider.TransactionProvider
import app.productbrain.data.repository.login.LoginDao
import app.productbrain.data.repository.login.LoginRepository
import org.koin.dsl.module

val dataModule = module {
    single { ClockProvider() }
    single { LoginRepository(get(), get(), get()) }
    single { TransactionProvider(get()) }

    single<LoginDao> { get<AppDatabase>().loginDao()  }
}