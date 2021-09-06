package com.castprogramms.newinvestgame.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.castprogramms.newinvestgame.tools.Stock

@Entity(foreignKeys = [ForeignKey(entity = Stock::class, parentColumns = ["name"], childColumns = ["stock_name"])])
class CostsOfStockForDataBase(val cost: Double, val stock_name: String, @PrimaryKey(autoGenerate = true) val id: Int = 0)