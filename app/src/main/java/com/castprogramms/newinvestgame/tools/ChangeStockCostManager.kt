package com.castprogramms.newinvestgame.tools

import com.castprogramms.newinvestgame.network.Repository
import com.castprogramms.newinvestgame.news.GeneratorNews
import com.castprogramms.newinvestgame.news.TypeEvent
import org.koin.java.KoinJavaComponent.inject

class ChangeStockCostManager(generatorNews: GeneratorNews) {
    private val repository: Repository by inject(Repository::class.java)

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
        if (repository.listStock.value != null) {
            repository.listStock.postValue(
                repository.listStock.value.apply {
                    this?.forEach {
                        if (it.companies == company)
                            it.updateCost(it.cost * (typeEvent.getIncreaseWithDelta()))
                    }
                }
            )
        }
    }

    private fun changePriceStockCountry(country: Countries, typeEvent: TypeEvent) {
        if (repository.listStock.value != null) {
            repository.listStock.postValue(
                repository.listStock.value.apply {
                    this?.forEach {
                        if (it.companies.country == country)
                            it.updateCost(it.cost * (typeEvent.getIncreaseWithDelta()))
                    }
                }
            )
        }
    }

    private fun changePriceStockIndustrial(industries: Industries, typeEvent: TypeEvent){
        if (repository.listStock.value != null) {
            repository.listStock.postValue(
                repository.listStock.value.apply {
                    this?.forEach {
                        if (it.companies.industry == industries)
                            it.updateCost(it.cost * (typeEvent.getIncreaseWithDelta()))
                    }
                }
            )
        }
    }
}