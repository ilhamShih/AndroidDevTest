package com.android.devtest.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.devtest.damain.loading.UseCaseLoadingImage
import com.android.devtest.damain.json.UseCaseGetJsonIthem
import com.android.devtest.data.GetProgresStatusIthemsImpl

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val getProgersStatusImpl = GetProgresStatusIthemsImpl
    private val useCaseGetJsonIthem = UseCaseGetJsonIthem(getProgersStatusImpl)
    private val useCaseLoadingImage = UseCaseLoadingImage(application, getProgersStatusImpl)
    val getJsonIthem = useCaseGetJsonIthem.getJsonIthem()
    val getImage = useCaseLoadingImage.getImage()
    val progeressLoader = useCaseLoadingImage.loaderProgress()

}