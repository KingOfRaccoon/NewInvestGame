package com.castprogramms.newinvestgame.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.tools.Stock

class ProfileFragmentLayout{
    var stocks = mutableStateOf(mapOf<Stock, Int>())

    @ExperimentalUnitApi
    @Preview
    @Composable
    fun Main() {
        Column {
            ProfileCard()
            ListOfStocks()
        }
    }

    @Composable
    fun ProfileCard() {
        MaterialTheme {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), elevation = 5.dp
            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.male_user),
                        contentDescription = "",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(50))
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    ConstraintLayout(modifier = Modifier.weight(3f)) {
                        val (nameUser, moneyUser, lossUser) = createRefs()
                        Text(
                            text = "Имя: Звездный бульк",
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .constrainAs(nameUser) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            textAlign = TextAlign.Start

                        )

                        Text(
                            text = "Звездный бульк",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .constrainAs(moneyUser) {
                                    top.linkTo(nameUser.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            textAlign = TextAlign.Start
                        )

                        Text(
                            text = "Звездный бульк",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .constrainAs(lossUser) {
                                    top.linkTo(moneyUser.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                },
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }

    @ExperimentalUnitApi
    @Composable
    fun ListOfStocks() {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(stocks.value.toList(), itemContent = { pair ->
                StockCard(pair)
            })
        }
    }

    @ExperimentalUnitApi
    @Composable
    fun StockCard(pair: Pair<Stock, Int>) {
        MaterialTheme {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 5.dp),
                elevation = 4.dp,
                shape = RoundedCornerShape(corner = CornerSize(16.dp))
            ) {
                ConstraintLayout(
                    Modifier
                        .fillMaxHeight()
                        .padding(10.dp)
                ) {
                    val (image, name, cost, quantity) = createRefs()

                    Image(
                        painterResource(pair.first.companies.img),
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
                        pair.first.companies.nameCompany,
                        Modifier
                            .fillMaxWidth()
                            .constrainAs(name) {
                                start.linkTo(image.end, 10.dp)
                                top.linkTo(parent.top)
                            },
                        fontSize = TextUnit(18f, TextUnitType.Sp)
                    )

                    Text(
                        pair.first.cost.toString(),
                        Modifier
                            .fillMaxWidth()
                            .constrainAs(cost) {
                                start.linkTo(image.end, 10.dp)
                                top.linkTo(name.bottom, 10.dp)
                            },
                        fontSize = TextUnit(16f, TextUnitType.Sp)
                    )

                    Text(
                        pair.second.toString(),
                        Modifier
                            .fillMaxHeight()
                            .constrainAs(quantity) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            },
                        fontSize = TextUnit(16f, TextUnitType.Sp)
                    )
                }
            }
        }
    }
}