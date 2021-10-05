package com.castprogramms.newinvestgame.ui.stock

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.FragmentStockBinding
import com.castprogramms.newinvestgame.tools.Stock
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import org.koin.androidx.viewmodel.ext.android.viewModel

class StockFragment : Fragment(R.layout.fragment_stock) {
    val viewModel: StockViewModel by viewModel()
    val stock: Stock by lazy {
        viewModel.getStock(requireArguments().getString("stock", ""))!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("data", stock.toString())
        requireActivity().title = "Сведения об акциях " + stock.name
        val binding = FragmentStockBinding.bind(view)
        binding.graph.viewport.setMinX(0.0)
        binding.graph.viewport.isXAxisBoundsManual = true
        binding.graph.viewport.isScalable = true
        binding.graph.viewport.setScalableY(true)
        binding.graph.viewport.scrollToEnd()

        stock.costsOfStock.observe(viewLifecycleOwner, { list ->
            val listDataPoint = mutableListOf<DataPoint>()
            list.forEachIndexed { index, d ->
                if (!binding.graph.series.indices.contains(index))
                    listDataPoint.add(DataPoint(index.toDouble(), d))
            }
//            binding.graph.series.fi
            val series = LineGraphSeries(listDataPoint.toTypedArray()).apply { setAnimated(true) }
            binding.graph.series.add(series)

            binding.graph.viewport.scrollToEnd()
            binding.graph.viewport.setMinX(0.0)
        })
    }
}