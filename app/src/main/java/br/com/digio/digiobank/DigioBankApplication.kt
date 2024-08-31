package br.com.digio.digiobank

import android.app.Application
import br.com.digio.digiobank.data.di.DataModule
import br.com.digio.digiobank.domain.di.DomainModule
import br.com.digio.digiobank.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DigioBankApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        this.setupKoin()
    }

    private fun setupKoin() {
        initKoin()
        loadModules()
    }

    private fun initKoin() {
        startKoin { androidContext(this@DigioBankApplication) }
    }

    private fun loadModules() {
        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}