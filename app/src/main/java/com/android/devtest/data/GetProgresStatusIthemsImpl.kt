package com.android.devtest.data

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.webkit.URLUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.devtest.damain.loading.LoadingImageImpl
import com.android.devtest.damain.json.Raitings
import com.android.devtest.damain.json.RaitingsIthemsImpl
import com.android.devtest.damain.json.DataService
import com.android.devtest.damain.json.HostModel
import com.android.devtest.data.RetrofitResponse.retrofitInstanceServer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


object GetProgresStatusIthemsImpl : RaitingsIthemsImpl, LoadingImageImpl {
    /**  Json */
    private var raitings = MutableLiveData<List<Raitings>>()
    private var list = mutableListOf<Raitings>()
    /** Progress DownloadManager */
    private var progressLoadFile = MutableLiveData<Int>()
    /**  This is the flag to disable DownloadManager */
    private var downLoadFlag = false

    /**----------------------------Getting json from the server-----------------------------------*/
    override fun getJsonIthem(): LiveData<List<Raitings>> {
        retrofitInstanceServer!!.create(DataService::class.java)
            .getDataService().enqueue(object : Callback<HostModel> {
                override fun onResponse(call: Call<HostModel>, response: Response<HostModel>) {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (response.isSuccessful) {
                            response.body()?.let { model ->
                                model.mRaitings.apply {
                                    list.clear()
                                    list.add(Raitings(_0.image, _0.title))
                                    list.add(Raitings(_1.image, _1.title))
                                    list.add(Raitings(_2.image, _2.title))
                                    list.add(Raitings(_3.image, _3.title))
                                    list.add(Raitings(_5.image, _5.title))
                                    list.add(Raitings(_6.image, _6.title))
                                    list.add(Raitings(_7.image, _7.title))
                                    list.add(Raitings(_8.image, _8.title))

                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<HostModel?>, t: Throwable) {
                    t.printStackTrace()

                }
            })
        /**  get a copy of the sheet */
        raitings.value = list.toList()
        return raitings
    }

    /**----------------------------Call DownloadManager recursively-----------------------------------*/
    override fun loader(context: Context) {
        if (!downLoadFlag) {
            progressLoadFile.value = 0
            val copyList = list.toList()
            CoroutineScope(Dispatchers.IO).launch {
                for (i in copyList.indices) {
                    delay(500)
                    context.downloadFile(copyList[i].image, i)
                }
            }
        } else {
            progressLoadFile.value = 100
        }
    }
    /**----------------------------Here is the loading progress for the LinearProgressIndicator-----------------------------------*/

    override fun loaderProgress(): LiveData<Int> {
        return progressLoadFile
    }

    /**---------------------------- Here DownloadManager for downloading images-----------------------------------*/



    fun Context.downloadFile(uRl: String, image: Int) {
        val failName = URLUtil.guessFileName(uRl, null, null)
        val direct = File(getExternalFilesDir(null), "/Shihzamanapp")
        val putFile = Environment.getExternalStorageDirectory()
            .toString() + "/" + Environment.DIRECTORY_PICTURES + "/Shihzamanapp"
        val fileE = File(putFile, failName)
        if (!direct.exists()) {
            direct.mkdirs()
        }
        try {
            if (!fileE.exists()) {
                downLoadFlag = true
                val imageUri = Uri.parse(uRl)
                val request = DownloadManager.Request(imageUri)
                request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
                ).setMimeType("image/jpeg")
                    .setAllowedOverRoaming(false)
                    .setDestinationUri(Uri.fromFile(fileE))
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    .setTitle(image.toString())
                    .setDescription("Downloading...")
                val mgr = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                mgr.enqueue(request)
                progressLoadFile.value?.let { it ->
                    progressLoadFile.postValue(it + (100 / (raitings.value?.size ?: 0)))
                }
            } else {
                progressLoadFile.value?.let { it ->
                    progressLoadFile.postValue(it + (100 / (raitings.value?.size ?: 0)))
                }
                downLoadFlag = false
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


}