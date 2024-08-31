package br.com.digio.digiobank.presentation.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.digio.digiobank.databinding.ItemMainProductsBinding
import br.com.digio.digiobank.domain.model.Product

internal class ProductAdapter : RecyclerView.Adapter<ProductItemViewHolder>() {

    var products = emptyList<Product>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                ProductListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val binding = ItemMainProductsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int =
        products.size
}