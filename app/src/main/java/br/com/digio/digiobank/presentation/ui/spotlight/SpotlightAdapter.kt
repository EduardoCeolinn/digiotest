package br.com.digio.digiobank.presentation.ui.spotlight

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.digio.digiobank.databinding.ItemMainSpotlightBinding
import br.com.digio.digiobank.domain.model.Spotlight

internal class SpotlightAdapter : RecyclerView.Adapter<SpotlightItemViewHolder>() {

    var spotlights = emptyList<Spotlight>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                SpotlightListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightItemViewHolder {
        val binding = ItemMainSpotlightBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SpotlightItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpotlightItemViewHolder, position: Int) {
        holder.bind(spotlights[position])
    }

    override fun getItemCount(): Int =
        spotlights.size
}