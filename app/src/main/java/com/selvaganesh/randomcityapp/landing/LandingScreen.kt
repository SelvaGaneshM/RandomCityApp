package com.selvaganesh.randomcityapp.landing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.selvaganesh.randomcityapp.dataset.CityDataSource
import com.selvaganesh.randomcityapp.dataset.addCity
import com.selvaganesh.randomcityapp.dataset.cities
import com.selvaganesh.randomcityapp.utils.CustomToolbar
import com.selvaganesh.randomcityapp.utils.isLandscape
import com.selvaganesh.randomcityapp.utils.isTablet

@Composable
fun LandingScreen(navController: NavController) {

    val notify = rememberSaveable { mutableStateOf(emptyList<CityDataSource>()) }

    Column(
        modifier = Modifier.statusBarsPadding()
    ) {
        CustomToolbar("LandingScreen", true, onClick = {

        }, Color.Blue, callback = {
            if (notify.value.size < cities.size) notify.value += addCity(notify.value.size)
        })
        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(), contentAlignment = Alignment.TopStart
        ) {
            RecyclerView(cityData = notify.value, navController)
        }
    }
}

@Composable
fun UserCard(item: CityDataSource, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .fillMaxWidth()
            .wrapContentSize()
            .clickable(onClick = {
                val bundle = bundleOf("amount" to "amount")
                navController.navigate("detailed_screen")
            }),
        colors = CardDefaults.cardColors(
            containerColor = item.color
        ),
        shape = RoundedCornerShape(CornerSize(10.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = item.cityName, modifier = Modifier.padding(10.dp, 20.dp), color = Color.White
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = item.dateTimeStamp,
                modifier = Modifier.padding(10.dp, 20.dp),
                color = Color.White
            )
        }
    }
}

@Composable
fun RecyclerView(cityData: List<CityDataSource>, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (isTablet() && isLandscape()) 2 else 1),
        modifier = Modifier.fillMaxSize()
    ) {
        items(cityData) { item ->
            UserCard(item, navController)
        }
    }
}

@Preview
@Composable
fun LandingScreenPreview() {
    LandingScreen(navController = NavController(context = LocalContext.current))
}

