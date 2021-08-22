package com.castprogramms.newinvestgame.ui.news

import androidx.lifecycle.ViewModel
import com.castprogramms.newinvestgame.network.Repository

class NewsViewModel(private val repository: Repository): ViewModel() {
    fun getNews() = repository.getNews()
}