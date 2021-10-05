package com.castprogramms.newinvestgame

import android.app.Application
import androidx.room.Room
import com.castprogramms.newinvestgame.database.StockDataBase
import com.castprogramms.newinvestgame.network.Repository
import com.castprogramms.newinvestgame.ui.news.NewsViewModel
import com.castprogramms.newinvestgame.ui.stock.StockViewModel
import com.castprogramms.newinvestgame.ui.stocks.AllStockViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class InvestApplication: Application() {
    private val modules = module {
        single { Room.databaseBuilder(applicationContext, StockDataBase::class.java, "database").build() }
        single { Repository(get()) }
        viewModel { NewsViewModel(get()) }
        viewModel { MainActivityViewModel(get()) }
        viewModel { AllStockViewModel(get()) }
        viewModel { StockViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@InvestApplication)
            modules(modules)
        }
    }
}