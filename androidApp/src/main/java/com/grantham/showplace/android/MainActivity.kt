package com.grantham.showplace.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.grantham.showplace.android.navigation.ShowPlaceNavHost
import org.koin.compose.KoinContext
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                KoinContext() {
                    val navController = rememberNavController()
                    ShowPlaceNavHost(
                        navController = navController,
                        modifier = Modifier,
                    )
                }
            }
        }
    }
}