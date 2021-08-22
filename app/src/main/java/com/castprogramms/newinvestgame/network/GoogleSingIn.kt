package com.castprogramms.newinvestgame.network

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class GoogleSingIn : RegistrApi {
    var userLiveData = MutableLiveData<Resource<String>>()

    override fun singIn(task: Task<GoogleSignInAccount>) {
        try {
            userLiveData.postValue(
                Resource.Success(task.getResult(ApiException::class.java)?.id.toString())
            )
        } catch (e: ApiException) {
            userLiveData.postValue(Resource.Error(e.message))
        }
    }

    override fun singOut() {
        TODO("Not yet implemented")
    }
}