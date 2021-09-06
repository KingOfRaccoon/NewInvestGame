package com.castprogramms.newinvestgame.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.ItemStockBinding
import com.castprogramms.newinvestgame.tools.Stock
import com.castprogramms.newinvestgame.tools.lifecycleOwner

class ProfileStockAdapter : RecyclerView.Adapter<ProfileStockAdapter.ProfileStockViewHolder>() {
    var stocks = mutableListOf<Pair<Stock, Int>>()
        set(value) {
            field = value
            value.toList().indices.forEach { notifyItemInserted(it) }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileStockViewHolder {
        return ProfileStockViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_stock, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileStockViewHolder, position: Int) {
        holder.bind(stocks[position])
    }

    override fun getItemCount() = stocks.size

    inner class ProfileStockViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemStockBinding.bind(view)

        fun bind(stockAndQuantity: Pair<Stock, Int>) {
            binding.iconComp.setImageResource(stockAndQuantity.first.companies.img)
            binding.nameStock.text = stockAndQuantity.first.companies.nameCompany
            binding.quantityStock.text = stockAndQuantity.second.toString()
            val lifecycleOwner = itemView.context.lifecycleOwner()
            if (lifecycleOwner != null)
                stockAndQuantity.first.costsOfStock.observe(lifecycleOwner, {
                    binding.costStock.text = it.last().toString()
                })
        }
    }
}