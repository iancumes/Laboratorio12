package com.example.cameralocationapp.screens

import android.Manifest
import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationScreen(navController: NavController) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var location by remember { mutableStateOf<Location?>(null) }

    LaunchedEffect(Unit) {
        if (!locationPermissionState.status.isGranted) {
            locationPermissionState.launchPermissionRequest()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (locationPermissionState.status.isGranted) {
            if (location != null) {
                Text("Ubicación actual:")
                Spacer(modifier = Modifier.height(8.dp))
                Text("Latitud: ${location?.latitude}")
                Text("Longitud: ${location?.longitude}")
            } else {
                Text("Presiona el botón para obtener tu ubicación.")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                getCurrentLocation(fusedLocationClient) { loc ->
                    location = loc
                }
            }) {
                Icon(Icons.Filled.LocationOn, contentDescription = "Icono de Ubicación")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Obtener Ubicación")
            }
        } else {
            Text("La aplicación requiere permiso de ubicación.")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
                Icon(Icons.Filled.Lock, contentDescription = "Icono de Permiso")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Solicitar Permiso")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}


fun getCurrentLocation(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationObtained: (Location) -> Unit
) {
    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
        location?.let {
            onLocationObtained(it)
        }
    }
}
