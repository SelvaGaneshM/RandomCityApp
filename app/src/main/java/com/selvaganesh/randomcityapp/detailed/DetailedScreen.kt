package com.selvaganesh.randomcityapp.detailed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.selvaganesh.randomcityapp.dataset.CityDataSource
import com.selvaganesh.randomcityapp.utils.CustomToolbar
import com.selvaganesh.randomcityapp.utils.MapScreen


@Composable
fun DetailedScreen(navController: NavController, viewModel: DetailedScreenViewModel) {

    val countryDetails =
        navController.previousBackStackEntry?.savedStateHandle?.get<CityDataSource>("countryDetails")
    val dataSet by viewModel.dataSet.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCityDetails(
            countryDetails?.cityName!!, "AIzaSyA7NwwXqgDj16wO9Rp5VDfNTm41XFySoeM"
        )
    }

    Column(
        modifier = Modifier.statusBarsPadding()
    ) {
        CustomToolbar("${countryDetails?.cityName}", false, onClick = {
            navController.popBackStack()
        }, countryDetails?.color ?: Color.Blue, callback = {

        })
        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(), contentAlignment = Alignment.TopStart
        ) {
            dataSet?.let {
                viewModel.insertData(countryDetails?.cityName,it)
                MapScreen(it,countryDetails?.cityName)
            }
        }
    }
}

@Preview
@Composable
fun DetailedScreenPreview() {
    DetailedScreen(
        navController = NavController(context = LocalContext.current), viewModel = viewModel()
    )
}