package com.example.cameralocationapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido a la aplicación de Camara y ubicacion")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("camera_screen") }) {
            Text("Ir a Cámara")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("location_screen") }) {
            Text("Obtener Ubicación")
        }
    }
}
