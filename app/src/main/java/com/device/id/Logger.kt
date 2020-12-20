package com.device.id

import android.util.Log

object Logger {
    private const val TAG = "deviceId"
    var isdebuger = true

    fun e(msg: String) {
        if (isdebuger) Log.e(TAG, msg)
    }

    fun d(msg: String) {
        if (isdebuger) Log.d(TAG, msg)
    }

}