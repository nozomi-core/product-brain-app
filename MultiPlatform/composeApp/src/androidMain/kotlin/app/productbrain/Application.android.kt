package app.productbrain

import android.app.Application
import app.productbrain.data.di.androidDataModules
import app.productbrain.data.di.dataModule
import app.productbrain.di.androidModules
import app.productbrain.di.commonModule
import app.productbrain.di.serviceModule
import app.productbrain.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                commonModule,
                serviceModule,
                useCaseModule,
                dataModule,
                androidDataModules,
                androidModules
            )
            androidContext(this@AndroidApplication)
        }
    }
}