package com.example.asteroidradarcompose.main

import android.app.Application
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun MainScreen() {

    // Obtain the Application context from the LocalContext
    val application = LocalContext.current.applicationContext as Application

    // Create a ViewModelProvider.Factory instance
    val factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)

    // Get the MainViewModel instance using the factory
    val mainViewModel: MainViewModel = viewModel(factory = factory)

    AsyncImage(
        modifier = Modifier.fillMaxWidth(),
        model = "https://static.vecteezy.com/system/resources/thumbnails/021/968/643/small_2x/space-nebula-night-gallaxy-illustration-cosmos-universe-astronomy-generative-ai-photo.jpg",
        contentDescription = "Picture of the day"
    )
//    LazyColumn(content = )
}


