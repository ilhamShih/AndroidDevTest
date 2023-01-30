package com.android.devtest.damain.loading

import android.content.Context
import androidx.lifecycle.LiveData

interface LoadingImageImpl {
    /**-------- Downloading the image -----------*/
    fun loader(context: Context)
    /**--------Download process -----------*/
    fun loaderProgress(): LiveData<Int>
}
