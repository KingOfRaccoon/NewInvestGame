package com.castprogramms.newinvestgame.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.castprogramms.newinvestgame.tools.Stock

@Dao
interface StockDao {

    @Transaction @Query("SELECT * FROM stockfordatabase")
    fun getAllStocks(): LiveData<List<StockWithCostsForDataBase>>

    @Transaction @Query("SELECT * FROM stockfordatabase WHERE name = :name")
    fun getStock(name: String): LiveData<StockForDataBase>

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

    @Transaction
    fun addStockAndCost(stockAndCost: Pair<Stock, Int>){
        val stockWithCostsForDataBase = StockWithCostsForDataBase(stockAndCost)
        insertStock(stockWithCostsForDataBase.stockForDataBase)
        stockWithCostsForDataBase.listCost.forEach {
            insertCost(it)
        }
    }
}