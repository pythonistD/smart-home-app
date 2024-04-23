package com.cheboksarov.smarthome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import coil.compose.AsyncImage
import com.cheboksarov.smarthome.ui.theme.SmartHomeTheme

enum class SmartDevicesTypes {
    LIGHT,
    SMARTSPEAKER,
    HUMIDIFIER,
    SWITCH,
    TEMPERATURESENSOR,
    HUMIDITYSENSOR
}

@Composable
fun DeviceCard(modifier: Modifier, deviceName: String, devType: SmartDevicesTypes,
                content: @Composable () -> Unit)
{
    OutlinedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
           containerColor = MaterialTheme.colorScheme.secondaryContainer,

        ),
        shape = MaterialTheme.shapes.medium,
    ) {
        Image(
            painter =
                when (devType) {
                    SmartDevicesTypes.LIGHT ->  painterResource(R.drawable.bulb)
                    SmartDevicesTypes.SMARTSPEAKER ->  painterResource(R.drawable.smart_speaker)
                    SmartDevicesTypes.HUMIDIFIER -> TODO()
                    SmartDevicesTypes.SWITCH -> TODO()
                    SmartDevicesTypes.TEMPERATURESENSOR -> TODO()
                    SmartDevicesTypes.HUMIDITYSENSOR -> TODO()
                },
            contentDescription = null,
            modifier = Modifier
                .clickable {
                }
                .background(Color.White)
                .clip(CircleShape)
                .padding(8.dp)
                .fillMaxWidth()
                .height(150.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = deviceName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(8.dp)
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ){
            content()
        }


    }

}

@Composable
fun LampDeviceCard(deviceName: String = "Lamp"){
    DeviceCard(modifier = Modifier.padding(8.dp),
        deviceName = deviceName,
        devType = SmartDevicesTypes.LIGHT
    ) {
        Text(
            text = "On/Off"
        )
        var checked by remember {mutableStateOf(false)}
        Switch(checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
    }
}

@Composable
fun SpeakerDeviceCard(deviceName: String = "Speaker"){
    DeviceCard(modifier = Modifier.padding(8.dp),
        deviceName = deviceName,
        devType = SmartDevicesTypes.SMARTSPEAKER
    ) {
        Text(
            text = "On/Off"
        )
        var checked by remember {mutableStateOf(false)}
        Switch(checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
    }
}

fun createListOfDevices() : MutableState<List<Device>> {
  return mutableStateOf(
      listOf(
          Device("Lamp", SmartDevicesTypes.LIGHT),
          Device("Lamp 2", SmartDevicesTypes.LIGHT),
          Device("Lamp 3", SmartDevicesTypes.LIGHT),
          Device( "Speaker", SmartDevicesTypes.SMARTSPEAKER)
      )
  )
}

@Composable
fun DeviceCardGrid(paddingValues: PaddingValues, itemsToDisplay: MutableState<List<Device>>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = paddingValues,
        state = rememberLazyGridState()
    ) {
        for(device in itemsToDisplay.value){
            item{
                when(device.type){
                    SmartDevicesTypes.LIGHT -> LampDeviceCard(device.name)
                    SmartDevicesTypes.SMARTSPEAKER -> SpeakerDeviceCard(device.name)
                    SmartDevicesTypes.HUMIDIFIER -> TODO()
                    SmartDevicesTypes.SWITCH -> TODO()
                    SmartDevicesTypes.TEMPERATURESENSOR -> TODO()
                    SmartDevicesTypes.HUMIDITYSENSOR -> TODO()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDeviceCardGrid() {
    SmartHomeTheme {
        DeviceCardGrid(PaddingValues(8.dp), createListOfDevices())
    }
}

@Preview
@Composable
fun PreviewDeviceCard() {
    SmartHomeTheme {
        DeviceCard(
            modifier = Modifier.padding(8.dp),
            deviceName = "Alisa",
            devType = SmartDevicesTypes.SMARTSPEAKER
        ){
            Text(
                text = "On/Off"
            )
            var checked by remember {mutableStateOf(false)}
            Switch(checked = checked,
                onCheckedChange = {
                    checked = it
                }
            )
        }
    }
}
