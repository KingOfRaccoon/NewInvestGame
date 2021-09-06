package com.castprogramms.newinvestgame.ui.profile

import android.os.Bundle
import android.view.View
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.FragementProfileBinding
import com.castprogramms.newinvestgame.databinding.StandartFragmentBinding
import com.castprogramms.newinvestgame.tools.Companies
import com.castprogramms.newinvestgame.tools.Stock

class ProfileFragment : Fragment(R.layout.fragement_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().title = "Профиль"

        val binding = FragementProfileBinding.bind(view)
        val mutableMap = mutableMapOf<Stock, Int>()
        Companies.values().forEach {
            mutableMap[Stock((300..400).random().toDouble(), it)] = (1..3).random()
        }
        val adapter = ProfileStockAdapter()
        adapter.stocks = mutableMap.toList().toMutableList()
        binding.recyclerStocks.adapter = adapter
    }
}