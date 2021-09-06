package com.castprogramms.newinvestgame.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StockDao {

    @Transaction @Query("SELECT * FROM stockfordatabase")
    fun getAllStocks(): LiveData<List<StockWithCostsForDataBase>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStock(stockForDataBase: StockForDataBase)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCost(costsOfStockForDataBase: CostsOfStockForDataBase)

    @Transaction
    fun addStockAndCost(stockWithCostsForDataBase: StockWithCostsForDataBase){
        insertStock(stockWithCostsForDataBase.stockForDataBase)
        stockWithCostsForDataBase.listCost.forEach {
            insertCost(it)
        }
    }
}