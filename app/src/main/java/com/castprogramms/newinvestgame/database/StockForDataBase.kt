package com.castprogramms.newinvestgame.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.castprogramms.newinvestgame.tools.Stock

@Entity
class StockForDataBase(@PrimaryKey(autoGenerate = false) val name: String, val quantity: Int){
    constructor(pair: Pair<Stock, Int>): this(pair.first.companies.nameCompany, pair.second)
}