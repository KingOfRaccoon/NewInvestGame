package com.castprogramms.newinvestgame.tools

import androidx.lifecycle.MutableLiveData

data class Stock(var cost: Double, var companies: Companies): Up {
    val name : String
    get() = companies.nameCompany

    var costs : MutableList<Double> = mutableListOf() // Изменяемый список цен
    var costsOfStock = MutableLiveData(costs) // LiveData, которая хранит в себе список цен

    init {
        costs.add(cost)
        costsOfStock.value = costs
    }

    override fun update() { // Переопределение функции из интерфейса Up
        costs.add(cost) // Добавление в список новой серии значений
        costsOfStock.value = costs // Присвоение в хранилище списка с сериями данных
    }

    fun updateCost(newCost: Double){
        cost = newCost
        costs.add(newCost) // Добавление в список новой серии значений
        costsOfStock.value = costs // Присвоение в хранилище списка с сериями данных
    }

    override fun equals(other: Any?) =
        if (other is Stock)
            other.companies.nameCompany == this.companies.nameCompany
        else
            false
}