package com.mbh.moviebrowser.features.movieDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mbh.moviebrowser.databinding.ItemCastBinding
import com.mbh.moviebrowser.domain.Cast

class CreditsAdapter(var casts: List<Cast>) : RecyclerView.Adapter<CreditsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemCastBinding.inflate(layoutInflater, parent, false)
        return CreditsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CreditsViewHolder, position: Int) {
        holder.itemCastBinding.cast = casts[position];
    }

    override fun getItemCount(): Int {
        return casts.size;
    }

    fun updateCasts(casts: List<Cast>) {
        this.casts = casts;
        notifyDataSetChanged()
    }
}