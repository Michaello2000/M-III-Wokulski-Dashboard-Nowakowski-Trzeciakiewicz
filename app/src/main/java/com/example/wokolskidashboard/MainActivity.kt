package com.example.wokulskidashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.wokulskidashboard.ui.MainScreen
import com.example.wokulskidashboard.ui.theme.WokulskiDashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WokulskiDashboardTheme {
                MainScreen()
            }
        }
    }
}
