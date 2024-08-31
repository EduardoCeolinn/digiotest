package br.com.digio.digiobank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digio.digiobank.domain.usecase.GetProductsUseCase
import br.com.digio.digiobank.presentation.utils.ProductsResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class MainViewModel(
    private val useCase: GetProductsUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<ProductsResult>(ProductsResult.Initial)
    val products = _products.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            useCase.invoke()
                .onStart { _products.value = ProductsResult.Loading }
                .catch { throwable ->
                    _products.value = ProductsResult.Error(throwable.message.orEmpty())
                }
                .collect { products ->
                    _products.value = ProductsResult.Success(products)
                }
        }
    }
}
