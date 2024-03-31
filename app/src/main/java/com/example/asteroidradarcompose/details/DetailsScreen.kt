package com.example.asteroidradarcompose.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.asteroidradarcompose.R
import com.example.asteroidradarcompose.main.MainViewModel
import com.example.asteroidradarcompose.ui.theme.mainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(mainViewModel: MainViewModel) {

    val asteroid by mainViewModel.navigateToDetailScreen.observeAsState()
    val asteroidImage = asteroid?.let {
        if (it.isPotentiallyHazardous) R.drawable.asteroid_hazardous else R.drawable.asteroid_safe
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Asteroid Radar") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = mainColor
                ),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                Image(
                    painter = painterResource(
                        id = asteroidImage!!,
                    ),
                    contentDescription = "",
                )
                AsteroidProperty(
                    title = "Close approach date",
                    subtitle = asteroid!!.closeApproachDate
                )
                AsteroidProperty(
                    title = "Absolute magnitude",
                    subtitle = asteroid!!.absoluteMagnitude.toString() + " au"
                )
                AsteroidProperty(
                    title = "Estimated diameter",
                    subtitle = asteroid!!.estimatedDiameter.toString() + " km"
                )
                AsteroidProperty(
                    title = "Relative velocity",
                    subtitle = asteroid!!.relativeVelocity.toString() + " km/s"
                )
                AsteroidProperty(
                    title = "Distance from Earth",
                    subtitle = asteroid!!.distanceFromEarth.toString() + " au"
                )
            }
        }
    )
}

@Composable
fun AsteroidProperty(title: String, subtitle: String) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}