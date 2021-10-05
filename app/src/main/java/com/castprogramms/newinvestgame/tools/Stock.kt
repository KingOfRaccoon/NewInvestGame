package com.castprogramms.newinvestgame.tools

import androidx.lifecycle.MutableLiveData
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

data class Stock(var cost: Double, var companies: Companies): Up {
    val name : String
    get() = companies.nameCompany

    var costs : MutableList<Double> = mutableListOf() // Изменяемый список цен
    var costsOfStock = MutableLiveData(costs) // LiveData, которая хранит в себе список цен

    init {
        costs.add(formatCost(cost))
        costsOfStock.value = costs
    }

    override fun update() { // Переопределение функции из интерфейса Up
        costs.add(formatCost(cost)) // Добавление в список новой серии значений
        costsOfStock.value = costs // Присвоение в хранилище списка с сериями данных
    }

    fun updateCost(newCost: Double){
        cost = formatCost(newCost)
        costs.add(cost) // Добавление в список новой серии значений
        costsOfStock.value = costs // Присвоение в хранилище списка с сериями данных
    }

    override fun equals(other: Any?) =
        if (other is Stock)
            other.companies.nameCompany == this.companies.nameCompany
        else
            false

    private fun formatCost(currentCost: Double): Double {
        var decimal = BigDecimal(currentCost)
        decimal = decimal.setScale(2, RoundingMode.CEILING)

        return decimal.toDouble()
    }
}