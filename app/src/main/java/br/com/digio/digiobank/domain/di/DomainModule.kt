package br.com.digio.digiobank.domain.di

import br.com.digio.digiobank.domain.usecase.GetProductsUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val domainModule = module {
    factory<GetProductsUseCase> {
        GetProductsUseCase(get())
    }
}

internal object DomainModule {
    fun load() = loadKoinModules(domainModule)
}

