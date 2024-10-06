package com.selvaganesh.randomcityapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.selvaganesh.randomcityapp.landing.LandingScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultToolbar(title: String, onClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan),
        navigationIcon = {
            IconButton(onClick = { onClick() }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Menu Btn")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(title: String, onClick: () -> Unit, color: Color, callback: () -> Unit) {

    val landingScreenViewModel: LandingScreenViewModel = viewModel()
    var timeInSec by remember { mutableStateOf("") }
    val countDownStarted by remember { mutableStateOf(true) }

    LaunchedEffect(countDownStarted) {
        if (countDownStarted) {
            landingScreenViewModel.countDownFlow.collect {
                if (it.isNotEmpty())
                    if (it == "00") {
                        timeInSec = "00"
                        landingScreenViewModel.startCountDown(5)
                        callback.invoke()
                    } else
                        timeInSec = it
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(color = color)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Menu Btn",
                    tint = Color.White
                )
            }
            Text(
                text = title,
                color = Color.White
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "$timeInSec s",
                color = Color.White,
                modifier = Modifier.padding(end = 2.dp)
            )
        }
    }
}

@Preview
@Composable
fun CustomToolbarPreview() {
    CustomToolbar("Main Preview", onClick = {}, Color.Blue, callback = {})
}
