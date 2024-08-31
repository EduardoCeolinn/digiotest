package br.com.digio.digiobank.domain.repository

import br.com.digio.digiobank.domain.model.DigioProducts
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): Flow<DigioProducts>
}