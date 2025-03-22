package br.com.william.mercadobitcoin

import android.app.Application
import br.com.william.mercadobitcoin.data.di.networkModule
import br.com.william.mercadobitcoin.data.di.repositories
import br.com.william.mercadobitcoin.data.di.serviceHelperModule
import br.com.william.mercadobitcoin.domain.di.useCaseModule
import br.com.william.mercadobitcoin.domain.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
// e0458274-3a78-4eda-87f9-b5eda0b3a4f7
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            androidLogger()
            modules(
                viewModelModule,
                repositories,
                serviceHelperModule,
                networkModule,
                useCaseModule
            )
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}