package app.productbrain

import android.app.Application
import app.productbrain.data.di.androidDataModules
import app.productbrain.data.di.dataModule
import app.productbrain.di.androidModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule + androidDataModules + androidModules)
            androidContext(this@AndroidApplication)
        }
    }
}