package com.castprogramms.newinvestgame.network

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.castprogramms.newinvestgame.database.StockDataBase
import com.castprogramms.newinvestgame.news.GeneratorNews
import com.castprogramms.newinvestgame.tools.ChangeStockCostManager
import com.castprogramms.newinvestgame.tools.Companies
import com.castprogramms.newinvestgame.tools.Stock
import com.castprogramms.newinvestgame.tools.Updater

class Repository(private val stockDataBase: StockDataBase) {
    private val generatorNews = GeneratorNews()
    private val listStock = MutableLiveData(mutableListOf<Stock>())
    private val changeStockCostManager by lazy { ChangeStockCostManager(generatorNews, getListStock()) }
    private val handler = Handler(Looper.getMainLooper())
    private val updater = Updater(handler)

    init {
        updater.objectsToUpdate.add(generatorNews)
        changeStockCostManager
    }

    fun getNews() = generatorNews.news
    fun getListStock(): MutableLiveData<MutableList<Stock>> {
        if (listStock.value.isNullOrEmpty())
            listStock.postValue(startAllStocks)
        return listStock
    }

    companion object{
        var startAllStocks: MutableList<Stock> = mutableListOf(
            Stock(Companies.Apple.cent, Companies.Apple),
            Stock(Companies.Intel.cent, Companies.Intel),
            Stock(Companies.Twitter.cent, Companies.Twitter),
            Stock(Companies.Mailru.cent, Companies.Mailru),
            Stock(Companies.Facebook.cent, Companies.Facebook),
            Stock(Companies.Yandex.cent, Companies.Yandex),
            Stock(Companies.Amazon.cent, Companies.Amazon),
            Stock(Companies.MasterCard.cent, Companies.MasterCard),
            Stock(Companies.IBM.cent, Companies.IBM),
            Stock(Companies.GazProm.cent, Companies.GazProm),
            Stock(Companies.Lukoil.cent, Companies.Lukoil),
            Stock(Companies.CocaCola.cent, Companies.CocaCola),
            Stock(Companies.McDonalds.cent, Companies.McDonalds),
            Stock(Companies.Microsoft.cent, Companies.Microsoft),
            Stock(Companies.Huawei.cent, Companies.Huawei)
        )
    }

    fun stopGame(){
        handler.removeCallbacks(updater)
        updater.play = false
    }

    fun startGame(){
        handler.post(updater)
        updater.play = true
    }
}