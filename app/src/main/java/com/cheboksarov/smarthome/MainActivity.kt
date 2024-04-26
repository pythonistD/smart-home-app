package com.cheboksarov.smarthome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.generated.destinations.AddDeviceScreenDestination
import com.ramcosta.composedestinations.generated.destinations.MainScreenDestination
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.destination

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    val TAG: String= "MainActivity";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainScreeViewModel: MainScreeViewModel = viewModel<MainScreeViewModel>()

            DestinationsNavHost(navGraph = NavGraphs.root,
                dependenciesContainerBuilder = {
                    destination(destination = AddDeviceScreenDestination) {dependency(mainScreeViewModel)}
                    destination(destination = MainScreenDestination) {dependency(mainScreeViewModel)}
                }
            )
        }
    }
}

