package com.cheboksarov.smarthome

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainScreeViewModel: ViewModel() {
    var devices = mutableStateOf(createListOfDevices(createListOfRooms()))
        private set
    // TODO: добавить комнату, которая создаётся по умолчанию и её нельзя удалить
    var rooms = mutableStateOf(createListOfRooms())
        private set

    var selectedRoom = mutableStateOf(rooms.value.first())
        private set


    fun addNewDevice(name: String, types: SmartDevicesTypes, room: Room){
        val newDev = Device(name, types, mutableListOf(rooms.value[0], room))
        devices.value += newDev
    }

    fun addNewRoom(name: String){
        rooms.value += Room(name)
    }

    fun selectRoom(room: Room){
        selectedRoom.value = room
    }

}