package com.example.asteroidradarcompose.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
                    .fillMaxWidth()
                    .padding(innerPadding),
            ) {
                AsyncImage(
                    model = pictureOfDay?.url,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentDescription = "Image of the day",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
                Text(
                    text = "Image of the day",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
                LazyColumn {
                    items(asteroidList.size) { index ->
                        ListItem(
                            asteroidTitle = asteroidList[index].codename,
                            asteroidDate = asteroidList[index].closeApproachDate,
                            onCLick = {
                                mainViewModel.displayAsteroidDetails(asteroidList[index])
                                navController.navigate("details/${asteroidList[index].id}")
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
    onCLick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCLick)
            .padding(16.dp)
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
    }
}