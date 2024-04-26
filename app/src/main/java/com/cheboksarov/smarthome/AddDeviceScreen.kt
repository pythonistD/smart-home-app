package com.cheboksarov.smarthome

import android.graphics.drawable.shapes.Shape
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cheboksarov.smarthome.ui.theme.Shapes
import com.cheboksarov.smarthome.ui.theme.SmartHomeTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.MainScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

const val TAG: String = "Add Screeen"
@OptIn(ExperimentalMaterial3Api::class)
@Destination<RootGraph>
@Composable
fun AddDeviceScreen(
    navigator: DestinationsNavigator,
    mainScreeViewModel: MainScreeViewModel
) {
    SmartHomeTheme {
        Scaffold(
            topBar = {
                TopAppBar(title =
                    {
                        Text("Add device")
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigator.navigate(MainScreenDestination()) }) {
                            Icon(imageVector =  Icons.AutoMirrored.Filled.ArrowBack, "return btn")
                        }
                    }
                )
            },
            /*bottomBar = {
                BottomAppBar {

                }
            }*/
        ) {
            paddingValues ->
            NewDeviceOptions(paddingValues = paddingValues, mainScreeViewModel, navigator)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDeviceOptions(
    paddingValues: PaddingValues,
    mainScreeViewModel: MainScreeViewModel,
    navigator: DestinationsNavigator
){
    val rooms = mainScreeViewModel.rooms.value
    var textVal by remember { mutableStateOf("") }
    var expand by remember { mutableStateOf(false) }
    var expandType by remember { mutableStateOf(false) }
    var selectedRoom by remember { mutableStateOf(rooms[0]) }
    var selectedDeviceType by remember { mutableStateOf(SmartDevicesTypes.LIGHT) }

    var showDialog =  remember{mutableStateOf(false)}

    Column(modifier = Modifier
        .padding(paddingValues)
        .padding(start = 20.dp)
        .fillMaxWidth()
        .fillMaxHeight(),
        //horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Enter Device Name", modifier = Modifier.padding(10.dp))
        TextField(value = textVal, onValueChange = {newVal -> textVal = newVal}
                    , modifier = Modifier
                .padding(5.dp, 0.dp)
                .clip(shape = MaterialTheme.shapes.medium)
        )

        Spacer(modifier = Modifier
                            .height(10.dp)
        )
        Box{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.clickable { expand =!expand }
            ) {
                Text(text = "Choose Room")
                IconButton(onClick = { expand = true }) {
                  Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Expand dropdown")
                }
            }
            DropdownMenu(expanded = expand, onDismissRequest = { expand = false }, ) {
                for(room: Room in rooms){
                    DropdownMenuItem(
                        onClick = {
                            selectedRoom = room
                        },
                        text = { Text(text = room.name)},
                        modifier = Modifier
                            .background(if (selectedRoom.id == room.id)
                                MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
                    )
                }
            }
        }
        Box{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.clickable { expandType =!expandType }
            ) {
                Text(text = "Choose Type of the Device")
                IconButton(onClick = { expandType = true }) {
                    Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "Expand dropdown")
                }
            }
            DropdownMenu(expanded = expandType, onDismissRequest = { expandType = false }, ) {
                for(devType: SmartDevicesTypes in SmartDevicesTypes.entries){
                    DropdownMenuItem(
                        onClick = {
                            selectedDeviceType = devType
                        },
                        text = { Text(text = devType.name)},
                        modifier = Modifier
                            .background(if (devType == selectedDeviceType)
                                MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            onClick = {
            Log.i(TAG, mainScreeViewModel.devices.toString())
            if (textVal.isNotBlank()){
                mainScreeViewModel.addNewDevice(textVal, selectedDeviceType, selectedRoom)
                navigator.navigate(MainScreenDestination())
            } else {
                showDialog.value = true
            }
        },
            modifier = Modifier
                .width(100.dp),
        ) {
           //Icon(imageVector = Icons.Rounded.Done, contentDescription = "Submit a new device")
            Text(text = "Submit")
        }
        if (showDialog.value){
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog.value = false
                        },
                    ){
                        Text("OK")
                    }
                },
                title = {
                    Text("Device name is required")
                },
                text = {
                    Text("Please enter device name")
                }

            )


        }
    }
}


@Composable
@Preview
fun NewDeviceOptionsPreview(){
    SmartHomeTheme {
        //NewDeviceOptions(paddingValues = PaddingValues(5.dp), rooms = listOf("Kitchen", "Bathroom"))
    }
}
