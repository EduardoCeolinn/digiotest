package br.com.digio.digiobank.data.datasource

import br.com.digio.digiobank.data.model.DigioProductsResponse
import br.com.digio.digiobank.data.service.ProductsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class ProductsRemoteDataSourceImpl(
    private val service: ProductsService,
    private val dispatcher: CoroutineDispatcher
) : ProductsRemoteDataSource {
    override suspend fun getProducts(): Flow<DigioProductsResponse> = flow {
        val response = service.getProducts()
        emit(response)
    }.flowOn(dispatcher)
}
