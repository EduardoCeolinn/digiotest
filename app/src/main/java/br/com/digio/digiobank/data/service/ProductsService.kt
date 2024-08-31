package br.com.digio.digiobank.data.service

import br.com.digio.digiobank.data.model.DigioProductsResponse
import retrofit2.http.GET

internal interface ProductsService {
    @GET("$SANDBOX$PRODUCTS")
    suspend fun getProducts(): DigioProductsResponse

    private companion object {
        const val SANDBOX = "/sandbox"
        const val PRODUCTS = "/products"
    }
}
