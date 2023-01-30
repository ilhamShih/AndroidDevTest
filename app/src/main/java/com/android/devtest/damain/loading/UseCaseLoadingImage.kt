package com.android.devtest.damain.loading

import android.content.Context
import androidx.lifecycle.LiveData

/**--------UseCase image uploads -----------*/
class UseCaseLoadingImage(
    private val context: Context,
    private val mLoadingImageImpl: LoadingImageImpl
) {
    /**-------- Downloading the image -----------*/
    fun getImage() {
        return mLoadingImageImpl.loader(context)
    }
    /**--------Download process -----------*/
    fun loaderProgress(): LiveData<Int> {
        return mLoadingImageImpl.loaderProgress()
    }
}