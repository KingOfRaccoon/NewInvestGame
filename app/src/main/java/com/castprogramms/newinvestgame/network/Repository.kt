package com.castprogramms.newinvestgame.network

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.castprogramms.newinvestgame.news.GeneratorNews
import com.castprogramms.newinvestgame.tools.ChangeStockCostManager
import com.castprogramms.newinvestgame.tools.Stock
import com.castprogramms.newinvestgame.tools.Updater

class Repository {
    private val generatorNews = GeneratorNews()
    val listStock = MutableLiveData(mutableListOf<Stock>())
    val changeStockCostManager = ChangeStockCostManager(generatorNews)

    init {
        val handler = Handler(Looper.getMainLooper())
        val updater = Updater(handler)
        updater.objectsToUpdate.add(generatorNews)
        handler.post(updater)
    }

    fun getNews() = generatorNews.news
}