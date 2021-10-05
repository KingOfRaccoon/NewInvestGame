package com.castprogramms.newinvestgame.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StockForDataBase::class, CostsOfStockForDataBase::class], version = 1)
abstract class StockDataBase: RoomDatabase() {
    abstract fun stockDao(): StockDao
}