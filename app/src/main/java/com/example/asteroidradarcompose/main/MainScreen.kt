package com.example.asteroidradarcompose.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.asteroidradarcompose.R
import com.example.asteroidradarcompose.ui.theme.mainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val pictureOfDay by mainViewModel.pictureOfDay.observeAsState()
    val asteroidList by mainViewModel.asteroidsList.observeAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Asteroid Radar") },
                colors = topAppBarColors(
                    containerColor = mainColor
                ),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                ) {
                    AsyncImage(
                        model = pictureOfDay?.url,
                        contentDescription = "Image of the day",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )
                    Text(
                        text = "Image of the Day",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .background(color = Color(0x55010613))
                            .padding(16.dp)
                            .fillMaxWidth(),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(asteroidList) { asteroid ->
                        ListItem(
                            asteroidTitle = asteroid.codename,
                            asteroidDate = asteroid.closeApproachDate,
                            isPotentiallyHazardous = asteroid.isPotentiallyHazardous,
                            onCLick = {
                                mainViewModel.displayAsteroidDetails(asteroid)
                                navController.navigate("details/${asteroid.id}")
                            }
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun ListItem(
    asteroidTitle: String,
    asteroidDate: String,
    isPotentiallyHazardous: Boolean,
    onCLick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCLick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = asteroidTitle,
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = asteroidDate,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(
                id = if (isPotentiallyHazardous) R.drawable.ic_status_potentially_hazardous
                else R.drawable.ic_status_normal
            ),
            contentDescription = null
        )
    }
}