package com.example.asteroidradarcompose.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.asteroidradarcompose.R
import com.example.asteroidradarcompose.ui.theme.AsteroidRadarComposeTheme

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    // Obtain the Application context from the LocalContext
//    val application = LocalContext.current.applicationContext as Application
//
//    // Get the MainViewModel instance using the default factory
//    val mainViewModel: MainViewModel = viewModel()
    val pictureOfDay by mainViewModel.pictureOfDay.observeAsState()
    val asteroidList by mainViewModel.asteroidsList.observeAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        AsyncImage(
//            model = "https://static.vecteezy.com/system/resources/thumbnails/021/968/643/small_2x/space-nebula-night-gallaxy-illustration-cosmos-universe-astronomy-generative-ai-photo.jpg",
            model = pictureOfDay?.url,
            placeholder = painterResource(id = R.drawable.placeholder),
            contentDescription = "Image of the day",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        // Display the text
        Text(
            text = "Image of the day",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        // Create a list of items
        LazyColumn {
            items(asteroidList.size) { index ->
                ListItem(
                    asteroidTitle = asteroidList[index].codename,
                    asteroidDate = asteroidList[index].closeApproachDate,
                    onCLick = { navController.navigate("details/${asteroidList[index].id}") }
                )
            }
        }
    }
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

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun GreetingPreview() {
    AsteroidRadarComposeTheme {
//        MainScreen(navController, mainViewModel)
    }
}