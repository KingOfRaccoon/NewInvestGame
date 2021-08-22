package com.castprogramms.newinvestgame.network.news

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NewsApi {
    @POST("predict")
    fun post(@Body requestNew: RequestNew): Call<ResponseNew>
}