package com.castprogramms.newinvestgame.ui.profile

import android.os.Bundle
import android.view.View
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.fragment.app.Fragment
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.StandartFragmentBinding
import com.castprogramms.newinvestgame.tools.Companies
import com.castprogramms.newinvestgame.tools.Stock

class ProfileFragment : Fragment(R.layout.standart_fragment) {
    @ExperimentalUnitApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().title = "Профиль"
        val binding = StandartFragmentBinding.bind(view)
        val layout = ProfileFragmentLayout()
        binding.compose.setContent {
            layout.Main()
            val mutableMap = mutableMapOf<Stock, Int>()
            Companies.values().forEach {
                mutableMap[Stock((300..400).random().toDouble(), it)] = (1..3).random()
            }
            layout.stocks.value = mutableMap.toMap()
        }
    }
}