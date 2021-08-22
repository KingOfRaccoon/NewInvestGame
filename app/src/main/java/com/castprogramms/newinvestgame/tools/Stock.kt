package com.castprogramms.newinvestgame.tools

import androidx.lifecycle.MutableLiveData
import com.jjoe64.graphview.series.DataPoint

data class Stock(var cost: Double, var companies: Companies): Up {

    var costs : MutableList<DataPoint> = mutableListOf() // Изменяемый список, который присваивается в LiveData
    var costsofStock = MutableLiveData(costs) // LiveData, которая хранит в себе изменяемый список DataPoint

    override fun update() { // Переопределение функции из интерфейса Up
        costs.add(DataPoint(costs.size.toDouble(), cost)) // Добавление в список новой серии значений
        costsofStock.value = costs // Присвоение в хранилище списка с сериями данных
    }

    fun updateCost(newCost: Double){
        cost = newCost
        costs.add(DataPoint(costs.size.toDouble(), newCost)) // Добавление в список новой серии значений
        costsofStock.value = costs // Присвоение в хранилище списка с сериями данных
    }

    override fun equals(other: Any?) =
        if (other is Stock)
            other.companies.nameCompany == this.companies.nameCompany
        else
            false
}