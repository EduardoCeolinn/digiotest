package br.com.digio.digiobank.data.datasource

import br.com.digio.digiobank.data.model.DigioProductsResponse
import kotlinx.coroutines.flow.Flow

internal interface ProductsRemoteDataSource {
    suspend fun getProducts(): Flow<DigioProductsResponse>
}
