package com.cheboksarov.smarthome

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cheboksarov.smarthome.ui.theme.SmartHomeTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.AddDeviceScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>(
    start = true
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    mainScreeViewModel: MainScreeViewModel
) {
    val TAG: String= "MainScreen";
    var expand by remember {mutableStateOf(false)}
    SmartHomeTheme {
        // A surface container using the 'background' color from the theme
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
                             IconButton(onClick =
                                {
                                    expand =!expand
                                }
                             ) {
                                 Icon(
                                     imageVector = Icons.Filled.Menu,
                                     contentDescription = "Menu"
                                 )
                                 DropdownMenu(expanded = expand, onDismissRequest = { expand = false }) {
                                     mainScreeViewModel.rooms.value.map { room ->
                                         DropdownMenuItem(
                                             text = { Text(room.name) },
                                             onClick = { mainScreeViewModel.selectRoom(room) },
                                             modifier = Modifier.background(
                                                 if(mainScreeViewModel.selectedRoom.value == room)
                                                     MaterialTheme.colorScheme.primaryContainer
                                                        else MaterialTheme.colorScheme.surface
                                             )
                                         )
                                     }
                                 }
                             }
                        },
                        actions = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit")
                            }
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        //mainScreeViewModel.addNewDevice("New Device", SmartDevicesTypes.LIGHT)
                        navigator.navigate(AddDeviceScreenDestination())

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
                // Задесь прередаю устройства из viewmodel
                var devicesToDisplay = mainScreeViewModel.devices.value.filter { it.room.contains(mainScreeViewModel.selectedRoom.value) }
                DeviceCardGrid(paddingValues = paddingValues, devicesToDisplay)
            }
        }
    }

}

