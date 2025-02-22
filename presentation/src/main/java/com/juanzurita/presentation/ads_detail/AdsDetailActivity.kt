package com.juanzurita.presentation.ads_detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juanzurita.presentation.ads_detail.ui.theme.IdealistaTechTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdsDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IdealistaTechTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()
              ) { innerPadding ->
                    AdDetailScreen(innerPadding)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IdealistaTechTestTheme {
        AdDetailScreen(PaddingValues(0.dp))
    }
}