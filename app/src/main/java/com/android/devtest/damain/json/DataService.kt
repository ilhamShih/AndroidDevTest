package com.android.devtest.damain.json

import retrofit2.Call
import retrofit2.http.GET


interface DataService {
    @GET("/testAndroidData")
    fun getDataService(): Call<HostModel>

}