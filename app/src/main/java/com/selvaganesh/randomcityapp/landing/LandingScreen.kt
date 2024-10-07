package com.selvaganesh.randomcityapp.landing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.selvaganesh.randomcityapp.dataset.CityDataSource
import com.selvaganesh.randomcityapp.dataset.addCity
import com.selvaganesh.randomcityapp.dataset.cities
import com.selvaganesh.randomcityapp.utils.CustomToolbar

@Composable
fun LandingScreen() {

    val notify = remember { mutableStateOf(emptyList<CityDataSource>()) }

    Column(
        modifier = Modifier.statusBarsPadding()
    ) {
        CustomToolbar("LandingScreen", onClick = {

        }, Color.Blue, callback = {
            if (notify.value.size < cities.size)
                notify.value += addCity(notify.value.size)
        })
        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(), contentAlignment = Alignment.TopStart
        ) {
            RecyclerView(cityData = notify.value)
        }
    }
}

@Composable
fun UserCard(userDetail: String, userDetails: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 5.dp)
            .fillMaxWidth()
            .wrapContentSize(),
        shape = RoundedCornerShape(CornerSize(10.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        Row(modifier = Modifier.padding(5.dp)) {
            Text(text = userDetail, modifier = Modifier.padding(10.dp, 20.dp))
            Spacer(Modifier.weight(1f))
            Text(text = userDetails, modifier = Modifier.padding(10.dp, 20.dp))
        }
    }
}

@Composable
fun RecyclerView(cityData: List<CityDataSource>) {
    LazyColumn {
        cityData
        items(items = cityData) {
            UserCard(it.cityName, it.dateTimeStamp)
        }
    }
}

@Preview
@Composable
fun LandingScreenPreview() {
    LandingScreen()
}