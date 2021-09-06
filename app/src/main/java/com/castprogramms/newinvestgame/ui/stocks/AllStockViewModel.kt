package com.castprogramms.newinvestgame.ui.stocks

import androidx.lifecycle.ViewModel
import com.castprogramms.newinvestgame.network.Repository

class AllStockViewModel(private val repository: Repository): ViewModel() {
    fun getAllStock() = repository.getListStock()
}