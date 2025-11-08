package app.productbrain

import android.app.Application
import app.productbrain.di.applicationModules
import app.productbrain.di.viewModelModules
import app.productbrain.feature.init.InitService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin

class ProApplication: Application() {
    private val initService by inject<InitService>()

    override fun onCreate() {
        super.onCreate()
        startKoin()
        startInitService()
    }

    private fun startKoin() {
        startKoin {
            modules(
                applicationModules,
                viewModelModules
            )
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun startInitService() {
        GlobalScope.launch {
            initService.create()
        }
    }
}
