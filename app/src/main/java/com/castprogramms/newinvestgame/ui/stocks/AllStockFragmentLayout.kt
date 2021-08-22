package com.castprogramms.newinvestgame.ui.stocks

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.castprogramms.newinvestgame.tools.Stock
import com.google.gson.Gson

class AllStockFragmentLayout(val goToStockFragment: (Bundle) -> Unit) {
    var stocks = mutableStateOf(listOf<Stock>())

    @Preview
    @ExperimentalMaterialApi
    @ExperimentalUnitApi
    @Composable
    fun Main() {
        ListOfStocks()
    }

    @ExperimentalMaterialApi
    @ExperimentalUnitApi
    @Composable
    fun ListOfStocks() {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(stocks.value, itemContent = { stock ->
                StockCard(stock)
            })
        }
    }

    @ExperimentalMaterialApi
    @ExperimentalUnitApi
    @Composable
    fun StockCard(stock: Stock) {
        MaterialTheme {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp, 15.dp, 5.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                onClick = { goToStockFragment(Bundle().apply { putString("stock", Gson().toJson(stock, Stock::class.java)) }) }
            ) {
                ConstraintLayout(
                    Modifier
                        .fillMaxHeight()
                        .padding(10.dp)
                ) {
                    val (image, name, cost) = createRefs()

                    Image(
                        painterResource(stock.companies.img),
                        "",
                        Modifier
                            .fillMaxWidth(0.2f)
                            .aspectRatio(1f)
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom)
                            }
                    )

                    Text(
                        stock.companies.nameCompany,
                        Modifier
                            .fillMaxWidth()
                            .constrainAs(name) {
                                start.linkTo(image.end, 10.dp)
                                top.linkTo(parent.top)
                            },
                        fontSize = TextUnit(18f, TextUnitType.Sp)
                    )

                    Text(
                        stock.cost.toString(),
                        Modifier
                            .fillMaxWidth()
                            .constrainAs(cost) {
                                start.linkTo(image.end, 10.dp)
                                top.linkTo(name.bottom, 10.dp)
                            },
                        fontSize = TextUnit(16f, TextUnitType.Sp)
                    )
                }
            }
        }
    }
}