package com.castprogramms.newinvestgame.ui.stocks

import android.os.Bundle
import android.view.View
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.StandartFragmentBinding
import com.castprogramms.newinvestgame.tools.Companies
import com.castprogramms.newinvestgame.tools.Stock

class AllStockFragment : Fragment(R.layout.standart_fragment) {

    @ExperimentalMaterialApi
    @ExperimentalUnitApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Биржа"
        val binding = StandartFragmentBinding.bind(view)
        val layout = AllStockFragmentLayout {
            findNavController().navigate(R.id.action_allStockFragment_to_stockFragment, it)
        }
        binding.compose.setContent {
            layout.Main()
            val listStock = mutableListOf<Stock>()
            Companies.values().forEach {
                listStock.add(Stock((300..400).random().toDouble(), it))
            }
            layout.stocks.value = listStock.toList()
        }
    }
}