package com.cheboksarov.smarthome

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Device(var name: String, val type: SmartDevicesTypes)
{
    companion object {
        private var c = 0
    }
    init {
        c++
    }
    val id:Int = c
}
