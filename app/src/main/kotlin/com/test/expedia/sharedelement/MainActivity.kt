package com.test.expedia.sharedelement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.test.expedia.sharedelement.ui.theme.SharedElementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.e("Ashok", "on onCreate")
        setContent {
            SharedElementTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    NestedSharedBoundsSample()
                    Log.e("Ashok","padding $innerPadding")
                    Home(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.e("Ashok", "on New Intent Clicked")
    }
}
