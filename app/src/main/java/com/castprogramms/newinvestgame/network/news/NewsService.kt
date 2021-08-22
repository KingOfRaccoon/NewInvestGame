package com.castprogramms.newinvestgame.network.news

import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object NewsService {
    private var mInstance: NewsService? = null
    private const val BASE_URL = "https://api.aicloud.sbercloud.ru/public/v1/public_inference/gpt3/"
    private var mRetrofit: Retrofit? = null

    init {
        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getInstance(): NewsService {
        if (mInstance == null) {
            mInstance = NewsService
        }
        return mInstance!!
    }

    fun getJSONApi(): NewsApi {
        return mRetrofit!!.create(NewsApi::class.java)
    }
}