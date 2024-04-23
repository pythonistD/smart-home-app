package com.cheboksarov.smarthome

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.cheboksarov.smarthome.ui.theme.SmartHomeTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Destination<RootGraph>(
    start = true
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val TAG: String= "MainScreen";
    SmartHomeTheme {
        // A surface container using the 'background' color from the theme
        val devices = remember {
            createListOfDevices()
        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Smart Home",
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        var dev: Device = Device("Test", SmartDevicesTypes.LIGHT)
                        Log.i(TAG, "Dev: id:${dev.id} name:${dev.name}")
                        devices.value += dev
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            ) {
                    paddingValues ->
                DeviceCardGrid(paddingValues = paddingValues, devices)
            }
        }
    }

}