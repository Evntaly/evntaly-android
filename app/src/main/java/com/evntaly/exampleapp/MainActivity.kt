package com.evntaly.exampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evntaly.exampleapp.ui.theme.EvntalyTheme
import com.evntaly.sdk.Evnt
import com.evntaly.sdk.EvntalySDK
import com.evntaly.sdk.EvntalyUser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EvntalySDK.init("", "", true);
        enableEdgeToEdge()
        setContent {
            EvntalyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.padding(it)) {
                        ActionButtons()
                    }
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun ActionButtons(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button(onClick = {
                EvntalySDK.sendEvent(
                    Evnt.Builder()
                        .title("Test Event")
                        .description("This is a test event")
                        .build()
                )
            }) {
                Text("Send Event")
            }

            Button(onClick = {
                EvntalySDK.identifyUser(
                    EvntalyUser.Builder()
                        .setId("0f6934fd-99c0-41ca-3333")
                        .setEmail("Alameer@evntaly.com")
                        .setFullName("Alameer Ashraf")
                        .setOrganization("Evntaly")
                        .setData(mapOf(
                            "Location" to "Egypt",
                            "timezone" to "Africa/Cairo"
                        ))
                        .build()
                )
            }) {
                Text("Identify User")
            }
        }
    }
}