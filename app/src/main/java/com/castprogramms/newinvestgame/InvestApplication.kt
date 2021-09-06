package com.castprogramms.newinvestgame

import android.app.Application
import com.castprogramms.newinvestgame.network.Repository
import com.castprogramms.newinvestgame.ui.news.NewsViewModel
import com.castprogramms.newinvestgame.ui.stocks.AllStockViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class InvestApplication: Application() {
    private val modules = module {
        single { Repository() }
        viewModel { NewsViewModel(get()) }
        viewModel { MainActivityViewModel(get()) }
        viewModel { AllStockViewModel(get()) }
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