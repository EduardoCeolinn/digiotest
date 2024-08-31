package br.com.digio.digiobank.data.di

import br.com.digio.digiobank.BuildConfig
import br.com.digio.digiobank.data.datasource.ProductsRemoteDataSource
import br.com.digio.digiobank.data.datasource.ProductsRemoteDataSourceImpl
import br.com.digio.digiobank.data.provider.factory.ApiFactory
import br.com.digio.digiobank.data.provider.factory.OkHttpClientFactory
import br.com.digio.digiobank.data.provider.factory.RetrofitFactory
import br.com.digio.digiobank.data.repository.ProductsRepositoryImpl
import br.com.digio.digiobank.data.service.ProductsService
import br.com.digio.digiobank.domain.repository.ProductsRepository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

// Dispatchers
const val DEFAULT_SCOPE = "DefaultScope"
const val MAIN_DISPATCHER = "MainDispatcher"
const val IO_DISPATCHER = "IODispatcher"
const val DEFAULT_DISPATCHER = "DefaultDispatcher"

val dispatcherModule = module {
    factory(named(DEFAULT_DISPATCHER)) { Dispatchers.Default }
    factory(named(IO_DISPATCHER)) { Dispatchers.IO }
    factory(named(MAIN_DISPATCHER)) { Dispatchers.Main }
    factory(named(DEFAULT_SCOPE)) { CoroutineScope(Dispatchers.Default) }
}

val apiModule = module {
    factory { ApiFactory.build(retrofit = get(), apiClass = ProductsService::class.java) }
}

val networkModule = module {
    factory { OkHttpClientFactory.build() }

    factory<Converter.Factory> {
        GsonConverterFactory.create(GsonBuilder().create())
    }

    factory {
        RetrofitFactory.build(
            url = BuildConfig.BASE_URL,
            client = get(),
            factory = get()
        )
    }
}

val repositoryModule = module {
    factory<ProductsRepository> {
        ProductsRepositoryImpl(productsRemoteDataSource = get())
    }
}

val dataSourceModule = module {
    factory<ProductsRemoteDataSource> {
        ProductsRemoteDataSourceImpl(
            service = get(),
            dispatcher = get(named(IO_DISPATCHER))
        )
    }
}

internal object DataModule {
    fun load() = loadKoinModules(
        networkModule + apiModule + repositoryModule +
                dataSourceModule + dispatcherModule
    )
}