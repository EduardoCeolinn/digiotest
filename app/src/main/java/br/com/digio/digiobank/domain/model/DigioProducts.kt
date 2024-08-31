package br.com.digio.digiobank.domain.model

data class DigioProducts(
    val cash: Cash,
    val products: List<Product> = emptyList(),
    val spotlight: List<Spotlight> = emptyList()
)
