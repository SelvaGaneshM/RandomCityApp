package com.selvaganesh.randomcityapp.detailed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.selvaganesh.randomcityapp.utils.CustomToolbar
import com.selvaganesh.randomcityapp.utils.MapScreen

@Composable
fun DetailedScreen(navController: NavController) {
    Column(
        modifier = Modifier.statusBarsPadding()
    ) {
        CustomToolbar("LandingScreen", false, onClick = {

        }, Color.Blue, callback = {

        })
        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(), contentAlignment = Alignment.TopStart
        ) {
            MapScreen()
        }
    }
}

@Preview
@Composable
fun DetailedScreenPreview() {
    DetailedScreen(navController = NavController(context = LocalContext.current))
}