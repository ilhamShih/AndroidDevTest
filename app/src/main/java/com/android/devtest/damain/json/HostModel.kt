package com.android.devtest.damain.json

import com.google.gson.annotations.SerializedName

/**--------Pojo Class -----------*/
class HostModel {
    @SerializedName("raitings")
    val mRaitings: Raitings = Raitings()

    class Raitings {
        @SerializedName("0")
        var _0: Raitings2 = Raitings2()
        @SerializedName("1")
        var _1: Raitings2 = Raitings2()
        @SerializedName("2")
        var _2: Raitings2 = Raitings2()
        @SerializedName("3")
        var _3: Raitings2 = Raitings2()
        @SerializedName("5")
        var _5: Raitings2 = Raitings2()
        @SerializedName("6")
        var _6: Raitings2 = Raitings2()
        @SerializedName("7")
        var _7: Raitings2 = Raitings2()
        @SerializedName("8")
        var _8: Raitings2 = Raitings2()
    }



    class Raitings2 {
        @SerializedName("image")
        val image: String = ""

        @SerializedName("title")
        val title: String = ""



    }

}

