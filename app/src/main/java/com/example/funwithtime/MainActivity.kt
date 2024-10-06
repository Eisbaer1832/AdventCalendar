package com.example.funwithtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.funwithtime.components.TimerCard

import com.example.funwithtime.navigation.AppNavigation
import com.example.funwithtime.navigation.Topbar
import com.example.funwithtime.ui.theme.BrainsToolboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BrainsToolboxTheme {
                HomeScreen()
                AppNavigation()
                Topbar()

            }

        }
    }
}



@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
    ) { _ ->
        // Screen content
        Scaffold(
            content = { contentPadding ->
                Column (
                    Modifier
                        .padding(top = 65.dp)
                ) {
                    TimerCard("Nächster Krams", 1755281101)

                    HorizontalDivider(color = MaterialTheme.colorScheme.secondary, thickness = 2.dp, modifier = Modifier.padding(horizontal = 18.dp))

                }
            }
        )
    }


}
