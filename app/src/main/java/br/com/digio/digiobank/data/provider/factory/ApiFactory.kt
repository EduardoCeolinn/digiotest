package br.com.digio.digiobank.data.provider.factory

import retrofit2.Retrofit

internal object ApiFactory {
    fun <T> build(retrofit: Retrofit, apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }
}
