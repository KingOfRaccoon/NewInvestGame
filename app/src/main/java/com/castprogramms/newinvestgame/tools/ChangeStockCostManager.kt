package com.castprogramms.newinvestgame.tools

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.castprogramms.newinvestgame.network.Repository
import com.castprogramms.newinvestgame.news.GeneratorNews
import com.castprogramms.newinvestgame.news.TypeEvent
import org.koin.java.KoinJavaComponent.inject

class ChangeStockCostManager(
    generatorNews: GeneratorNews,
    private val listStock: MutableLiveData<MutableList<Stock>>
) {

    init {
        generatorNews.news.observeForever {
            if (it.isNotEmpty()) {
                val new = it.last()
                when (new.placeInfluence) {
                    is PlaceInfluence.Company -> {
                        changePriceStockCompany(
                            (new.placeInfluence as PlaceInfluence.Company).company,
                            new.typeEvent
                        )
                    }
                    is PlaceInfluence.Country -> {
                        changePriceStockCountry(
                            (new.placeInfluence as PlaceInfluence.Country).country,
                            new.typeEvent
                        )
                    }
                    is PlaceInfluence.Industrial -> {
                        changePriceStockIndustrial(
                            (new.placeInfluence as PlaceInfluence.Industrial).industry,
                            new.typeEvent
                        )
                    }
                }
            }
        }
    }

    private fun changePriceStockCompany(company: Companies, typeEvent: TypeEvent) {
        Log.e("newsData", company.nameCompany + " " + typeEvent.name)
        if (listStock.value != null) {
            listStock.postValue(
                listStock.value.apply {
                    this?.forEach {
                        if (it.companies == company)
                            it.updateCost(it.cost * (typeEvent.getIncreaseWithDelta()))
                        else
                            it.updateCost(it.cost)
                    }
                }
            )
        }
    }

    private fun changePriceStockCountry(country: Countries, typeEvent: TypeEvent) {
        Log.e("newsData", country.nameCountry + " " + typeEvent.name)
        if (listStock.value != null) {
            listStock.postValue(
                listStock.value.apply {
                    this?.forEach {
                        if (it.companies.country == country)
                            it.updateCost(it.cost * (typeEvent.getIncreaseWithDelta()))
                        else
                            it.updateCost(it.cost)
                    }
                }
            )
        }
    }

    private fun changePriceStockIndustrial(industries: Industries, typeEvent: TypeEvent) {
        Log.e("newsData", industries.nameIndustry + " " + typeEvent.name)
        if (listStock.value != null) {
            listStock.postValue(
                listStock.value.apply {
                    this?.forEach {
                        if (it.companies.industry == industries)
                            it.updateCost(it.cost * (typeEvent.getIncreaseWithDelta()))
                        else
                            it.updateCost(it.cost)
                    }
                }
            )
        }
    }
}