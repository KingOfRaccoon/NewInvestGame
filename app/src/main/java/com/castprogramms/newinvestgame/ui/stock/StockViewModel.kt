package com.castprogramms.newinvestgame.ui.stock

import androidx.lifecycle.ViewModel
import com.castprogramms.newinvestgame.network.Repository

class StockViewModel(private val repository: Repository): ViewModel() {
    fun getStock(nameStock: String) = repository.getListStock().value?.find { it.name == nameStock }
}