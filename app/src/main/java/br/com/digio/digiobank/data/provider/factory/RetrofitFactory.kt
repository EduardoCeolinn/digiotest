package br.com.digio.digiobank.data.provider.factory

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

internal object RetrofitFactory {

    fun build(url: String, client: OkHttpClient, factory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(factory)
            .build()
    }
}
