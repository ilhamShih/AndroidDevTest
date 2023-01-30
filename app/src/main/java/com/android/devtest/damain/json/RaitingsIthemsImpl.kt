package com.android.devtest.damain.json

import androidx.lifecycle.LiveData

interface RaitingsIthemsImpl {
    /**-------- Server call -----------*/
    fun getJsonIthem(): LiveData<List<Raitings>>

}