package com.demo.blogdemoapp.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.blogdemoapp.screens.home.BlogPost

@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel()) {

    val detail by viewModel.details.collectAsState()

    if (detail.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (detail.error.isNotBlank()) {
        Box(Modifier.fillMaxSize()) {
            Text(text = detail.error, modifier = Modifier.align(Alignment.Center))
        }
    }

    detail.data?.let {
        Column {
            BlogPost(it) {}
            Column(Modifier.padding(horizontal = 13.dp)) {
                Text(text = "Views: " + it.views.toString())
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "User ID: " + it.userId.toString())
            }
        }

    }

}