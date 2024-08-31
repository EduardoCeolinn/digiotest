package br.com.digio.digiobank.presentation.ui.spotlight

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.digio.digiobank.R
import br.com.digio.digiobank.databinding.ItemMainSpotlightBinding
import br.com.digio.digiobank.domain.model.Spotlight
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

internal class SpotlightItemViewHolder(
    private val binding: ItemMainSpotlightBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: Spotlight) {
        with(binding) {
            imgMainItem.contentDescription = product.name
            progressImage.visibility = View.VISIBLE

            Picasso.get()
                .load(product.bannerURL)
                .error(R.drawable.ic_alert_circle)
                .into(imgMainItem, object : Callback {
                    override fun onSuccess() {
                        progressImage.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        progressImage.visibility = View.GONE
                    }
                })
        }
    }
}
