package com.castprogramms.newinvestgame.tools

open class PlaceInfluence {
    inner class Company(val company: Companies): PlaceInfluence()
    inner class Country(val country: Countries): PlaceInfluence()
    inner class Industrial(val industry: Industries): PlaceInfluence()

    fun getCompany() = Company(Companies.values().random())
    fun getCountry() = Country(Countries.values().random())
    fun getIndustrial() = Industrial(Industries.values().random())
}