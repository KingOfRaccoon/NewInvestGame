package com.castprogramms.newinvestgame.ui.stocks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.ItemStockBinding
import com.castprogramms.newinvestgame.tools.Stock
import com.castprogramms.newinvestgame.tools.lifecycleOwner
import com.google.gson.Gson

class AllStockAdapter : RecyclerView.Adapter<AllStockAdapter.AllStockViewHolder>() {
    var stocks = mutableListOf<Stock>()
        set(value) {
            field = value
            value.indices.forEach { notifyItemInserted(it) }
        }

    fun addStock(stock: Stock) {
        stocks.add(stock)
        notifyItemInserted(stocks.lastIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllStockViewHolder {
        return AllStockViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_stock, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllStockViewHolder, position: Int) {
        holder.bind(stocks[position])
    }

    override fun getItemCount() = stocks.size

    inner class AllStockViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemStockBinding.bind(view)

        fun bind(stock: Stock) {
            binding.quantityStock.visibility = View.GONE

            binding.iconComp.setImageResource(stock.companies.img)
            binding.nameStock.text = stock.name
            val lifecycleOwner = itemView.context.lifecycleOwner()
            if (lifecycleOwner != null)
                stock.costsOfStock.observe(lifecycleOwner, {
                    binding.costStock.text = it.last().toString()
                })

            binding.root.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_allStockFragment_to_stockFragment, Bundle().apply {
                        putString("stock", stock.name)
                    })
            }
        }
    }
}