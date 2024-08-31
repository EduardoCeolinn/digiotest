package br.com.digio.digiobank.data.repository

import br.com.digio.digiobank.data.datasource.ProductsRemoteDataSource
import br.com.digio.digiobank.data.mapper.toDomain
import br.com.digio.digiobank.domain.model.DigioProducts
import br.com.digio.digiobank.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ProductsRepositoryImpl(
    private val productsRemoteDataSource: ProductsRemoteDataSource
) : ProductsRepository {
    override suspend fun getProducts(): Flow<DigioProducts> = flow {
        productsRemoteDataSource.getProducts().collect { result ->
            emit(result.toDomain())
        }
    }
}
