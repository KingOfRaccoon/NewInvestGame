package com.castprogramms.newinvestgame.ui.stock

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.StandartFragmentBinding
import com.castprogramms.newinvestgame.tools.Stock
import com.google.gson.Gson
import com.jjoe64.graphview.series.DataPoint

class StockFragment : Fragment(R.layout.standart_fragment) {
    val stock: Stock by lazy {
        Gson().fromJson(
            requireArguments().get("stock").toString(),
            Stock::class.java
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Сведения об акциях " + stock.companies.nameCompany
        val layout = StockFragmentLayout()
        StandartFragmentBinding.bind(view).compose.setContent {
            layout.Main()
        }
        layout.costOfStock.value =
            MutableList(100) { DataPoint(it.toDouble(), (0..1000).random().toDouble()) }
    }
}