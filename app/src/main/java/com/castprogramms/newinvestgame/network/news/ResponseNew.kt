package com.castprogramms.newinvestgame.network.news

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseNew(@SerializedName("predictions") @Expose val predictions: String)
