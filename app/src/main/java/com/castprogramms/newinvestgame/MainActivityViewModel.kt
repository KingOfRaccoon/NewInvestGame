package com.castprogramms.newinvestgame

import androidx.lifecycle.ViewModel
import com.castprogramms.newinvestgame.network.Repository

class MainActivityViewModel(private val repository: Repository): ViewModel() {
    fun getNews() = repository.getNews()
}