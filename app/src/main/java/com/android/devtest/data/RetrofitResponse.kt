package com.android.devtest.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**-------- Retrofit Client -----------*/
object RetrofitResponse {
    private var retrofit: Retrofit? = null
    val retrofitInstanceServer: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://wowowcleaner.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}