package com.cheboksarov.smarthome

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Device(var name: String, val type: SmartDevicesTypes, var room: MutableList<Room>)
{
    companion object {
        private var c = 0
    }
    init {
        c++
    }
    val id:Int = c
}

enum class SmartDevicesTypes {
    LIGHT,
    SMARTSPEAKER,
    HUMIDIFIER,
    SWITCH,
    TEMPERATURESENSOR,
    HUMIDITYSENSOR
}

fun createListOfDevices(rooms: List<Room>): List<Device>{
   return listOf(
       Device("Lamp", SmartDevicesTypes.LIGHT, mutableListOf(rooms[0], rooms[1])),
       Device("Lamp 2", SmartDevicesTypes.LIGHT, mutableListOf(rooms[0], rooms[1])),
       Device("Lamp 3", SmartDevicesTypes.LIGHT, mutableListOf(rooms[0], rooms[2])),
       Device( "Speaker", SmartDevicesTypes.SMARTSPEAKER, mutableListOf(rooms[0], rooms[2])),
   )
}
