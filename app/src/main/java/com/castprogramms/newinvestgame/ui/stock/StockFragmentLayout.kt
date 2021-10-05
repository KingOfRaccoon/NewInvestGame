package com.castprogramms.newinvestgame.ui.stock

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class StockFragmentLayout {
    val costOfStock = mutableStateOf(mutableListOf<DataPoint>())

    @Preview
    @Composable
    fun Main() {
        LazyColumn{
            item { StockGraphView() }
            item { Spacer(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)) }
        }
    }

    @Composable
    fun StockGraphView() {
        AndroidView(factory = {
            GraphView(it).apply {
                this.minimumHeight = 800
                this.viewport.isScrollable = true
                this.viewport.isScalable = true

//                this.viewport.isXAxisBoundsManual = true
                this.gridLabelRenderer.padding = 50
                this.gridLabelRenderer.verticalAxisTitle = "Цена"
                this.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            }
        },
            Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            update = {
                it.series.clear()
                it.addSeries(LineGraphSeries(costOfStock.value.toTypedArray()).apply {
                    setAnimated(true)
                })
                it.viewport.scrollToEnd()
                it.viewport.setMinX(0.0)
                it.viewport.setMinY(0.0)
            }
        )
    }

}