package com.castprogramms.newinvestgame.database

import androidx.room.Embedded
import androidx.room.Relation
import com.castprogramms.newinvestgame.tools.Stock

class StockWithCostsForDataBase(
    @Embedded val stockForDataBase: StockForDataBase,
    @Relation(parentColumn = "name", entity = StockForDataBase::class, entityColumn = "stock_name")
    val listCost: List<CostsOfStockForDataBase>
){
    constructor(pair: Pair<Stock, Int>): this(StockForDataBase(pair), pair.first.costs.map { CostsOfStockForDataBase(it, pair.first.companies.nameCompany) })
}