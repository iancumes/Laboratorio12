package com.example.cameralocationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import com.example.cameralocationapp.ui.theme.CameralocationappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameralocationappTheme {
                AppNavigation()
            }
        }
    }
}
