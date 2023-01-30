package com.android.devtest.presentation.helper

import android.content.Context
import android.content.Context.MODE_PRIVATE
import kotlin.random.Random

object Helper {
    var mainGlobalFloat  = 0F
    var mainGlobalInt  = 0

    var Context.permissionSave: Boolean
        get() = getSharedPreferences(packageName, MODE_PRIVATE)
            .getBoolean("permission", false)
        set(p) {
            getSharedPreferences(packageName, MODE_PRIVATE).edit()
                .putBoolean("permission", p).apply()
        }

    fun onGenerator(): Set<Long> {
        val hashSet = mutableSetOf<Long>()
        while (hashSet.size < 2) {
            when (Random.nextInt(1, 5)) {
                1 -> hashSet.add(100L)
                2 -> hashSet.add(200L)
                3 -> hashSet.add(300L)
                4 -> hashSet.add(400L)
                5 -> hashSet.add(500L)
                else -> {
                    hashSet.add(100L)
                }
            }
        }
        return hashSet
    }
}