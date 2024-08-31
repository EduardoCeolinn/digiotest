package br.com.digio.digiobank.data.model

internal data class DigioProductsResponse (
    val cash: CashResponse?,
    val products: List<ProductResponse>?,
    val spotlight: List<SpotlightResponse>?
)