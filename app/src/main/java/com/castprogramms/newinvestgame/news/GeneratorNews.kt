package com.castprogramms.newinvestgame.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.castprogramms.newinvestgame.network.news.NewsService
import com.castprogramms.newinvestgame.network.news.RequestNew
import com.castprogramms.newinvestgame.network.news.ResponseNew
import com.castprogramms.newinvestgame.tools.PlaceInfluence
import com.castprogramms.newinvestgame.tools.Up
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GeneratorNews : Up {
    val news = MutableLiveData(mutableListOf<New>())

    override fun update() {
        createTitle()
    }

    private fun createTitle() {
        val typeEvent = TypeEvent.values().random()
        when (val place = getPlace()) {
            is PlaceInfluence.Company -> {
                createFullNew(
                    NewsPattern(typeEvent, place.company).toString(),
                    place,
                    typeEvent
                )
            }

            is PlaceInfluence.Country -> {
                createFullNew(
                    NewsPattern(typeEvent, place.country).toString(),
                    place,
                    typeEvent
                )
            }

            is PlaceInfluence.Industrial -> {
                createFullNew(
                    NewsPattern(typeEvent, place.industry).toString(),
                    place,
                    typeEvent
                )
            }
        }
    }

    private fun createFullNew(title: String, place: PlaceInfluence, typeEvent: TypeEvent) {
        Log.e("data", title)
        NewsService.getInstance().getJSONApi()
            .post(RequestNew(title))
            .enqueue(object : Callback<ResponseNew> {
                override fun onResponse(call: Call<ResponseNew>, response: Response<ResponseNew>) {
                    Log.e("sus", response.body()?.predictions.toString())
                    news.postValue(news.value?.apply {
                        add(
                            0,
                            New(
                                typeEvent,
                                place,
                                response.body()?.predictions.toString()
                            )
                        )
                    })
                }

                override fun onFailure(call: Call<ResponseNew>, t: Throwable) {
                    Log.e("error", t.message.toString())
                }
            })
    }

    private fun getPlace(): PlaceInfluence {
        val place = PlaceInfluence()
        return when ((0..2).random()) {
            0 -> place.getCompany()
            1 -> place.getCountry()
            2 -> place.getIndustrial()
            else -> place.getCountry()
        }
    }
}