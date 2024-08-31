package br.com.digio.digiobank.domain.usecase

import br.com.digio.digiobank.domain.model.DigioProducts
import br.com.digio.digiobank.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(): Flow<DigioProducts> {
        return productsRepository.getProducts()
    }
}
