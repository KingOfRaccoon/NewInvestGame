package com.castprogramms.newinvestgame.news

import com.castprogramms.newinvestgame.tools.Industries
import com.castprogramms.newinvestgame.tools.Companies
import com.castprogramms.newinvestgame.tools.Countries

class NewsPattern() {
    private var message = ""

    constructor(typeEvent: TypeEvent, companies: Companies): this(){
        val nameCompany = arrayOf("компании ", "организации ", "корпорации ")
        message = "В " + nameCompany.random() + companies.nameCompany + typeEvent.listDesc.random()
    }

    constructor(typeEvent: TypeEvent, countries: Countries): this(){
        val nameCountry = arrayOf("государстве ", "стране ")
        message = "В " + nameCountry.random() + countries.nameCountry + typeEvent.listDesc.random()
    }

    constructor(typeEvent: TypeEvent, industries: Industries): this(){
        message = industries.listName.random() + typeEvent.listDesc.random()
    }

    override fun toString() = message
}