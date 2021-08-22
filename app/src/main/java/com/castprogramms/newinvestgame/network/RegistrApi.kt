package com.castprogramms.newinvestgame.network

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

interface RegistrApi {
    fun singIn(task: Task<GoogleSignInAccount>)

    fun singOut()
}