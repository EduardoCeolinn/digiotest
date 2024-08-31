package br.com.digio.digiobank.data.mapper

import br.com.digio.digiobank.data.model.CashResponse
import br.com.digio.digiobank.data.model.DigioProductsResponse
import br.com.digio.digiobank.data.model.ProductResponse
import br.com.digio.digiobank.data.model.SpotlightResponse
import br.com.digio.digiobank.domain.model.Cash
import br.com.digio.digiobank.domain.model.DigioProducts
import br.com.digio.digiobank.domain.model.Product
import br.com.digio.digiobank.domain.model.Spotlight

internal fun DigioProductsResponse.toDomain() = DigioProducts(
    cash = this.cash?.toDomain() ?: Cash(),
    products = this.products?.map { it.toDomain() } ?: emptyList(),
    spotlight = this.spotlight?.map { it.toDomain() } ?: emptyList(),
)

internal fun CashResponse.toDomain() = Cash(
    bannerURL = bannerURL.orEmpty(),
    title = title.orEmpty()
)

internal fun ProductResponse.toDomain() = Product(
    imageURL = imageURL.orEmpty(),
    name = name.orEmpty(),
    description = description.orEmpty()
)

internal fun SpotlightResponse.toDomain() = Spotlight(
    bannerURL = bannerURL.orEmpty(),
    name = name.orEmpty(),
    description = description.orEmpty()
)
