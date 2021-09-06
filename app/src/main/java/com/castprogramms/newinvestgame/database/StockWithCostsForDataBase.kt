package com.castprogramms.newinvestgame.database

import androidx.room.Embedded
import androidx.room.Relation

class StockWithCostsForDataBase(
    @Embedded val stockForDataBase: StockForDataBase,
    @Relation(parentColumn = "name", entity = StockForDataBase::class, entityColumn = "stock_name")
    val listCost: List<CostsOfStockForDataBase>
)