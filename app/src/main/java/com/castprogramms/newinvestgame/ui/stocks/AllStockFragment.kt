package com.castprogramms.newinvestgame.ui.stocks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.FragmentAllStockBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllStockFragment : Fragment(R.layout.fragment_all_stock) {
    private val viewModel : AllStockViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Биржа"
        val binding = FragmentAllStockBinding.bind(view)
        val adapter = AllStockAdapter()
        binding.recyclerAllStocks.adapter = adapter

        viewModel.getAllStock().observe(viewLifecycleOwner, {
            it.minus(adapter.stocks).forEach {
                adapter.addStock(it)
            }
        })

    }
}