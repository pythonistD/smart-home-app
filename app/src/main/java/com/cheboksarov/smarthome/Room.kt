package com.cheboksarov.smarthome

data class Room(var name: String)
{
    companion object {
        private var c = 0
    }
    init {
        c++
    }
    val id:Int = c
}

fun createListOfRooms(): List<Room> {
    return listOf(
        Room("All"),
        Room("Living Room"),
        Room("Bedroom"),
        Room("Kitchen"),
    )
}
