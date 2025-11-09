package app.productbrain

import android.app.Application
import app.productbrain.data.di.dataModule
import org.koin.core.context.startKoin

class ProAndroidApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModule)
        }
    }
}