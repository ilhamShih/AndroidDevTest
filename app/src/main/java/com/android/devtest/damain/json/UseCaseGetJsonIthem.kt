package com.android.devtest.damain.json

import androidx.lifecycle.LiveData

class UseCaseGetJsonIthem(private val raitingsIthemsImpl: RaitingsIthemsImpl) {
    /**-------- Server call -----------*/
    fun getJsonIthem(): LiveData<List<Raitings>> {
        return raitingsIthemsImpl.getJsonIthem()
    }

}