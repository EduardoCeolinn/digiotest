package br.com.digio.digiobank.data.provider.factory

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val DEFAULT_TIME: Long = 60

internal object OkHttpClientFactory {

    fun build(): OkHttpClient {
        return OkHttpClient.Builder()
            .setupTimeout()
            .build()
    }

    private fun OkHttpClient.Builder.setupTimeout() = apply {
        readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
        writeTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
        connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
    }
}
