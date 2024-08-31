package br.com.digio.digiobank.presentation.utils

import br.com.digio.digiobank.domain.model.DigioProducts

sealed class ProductsResult {
    data object Initial : ProductsResult()
    data class Success(val products: DigioProducts) : ProductsResult()
    data class Error(val message: String) : ProductsResult()
    data object Loading : ProductsResult()
}