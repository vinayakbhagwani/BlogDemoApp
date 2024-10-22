package com.demo.blogdemoapp.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.demo.domain.model.Blog

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

//    val blogs by viewModel.blogs.collectAsState()


//    if (blogs.isLoading) {
//        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            CircularProgressIndicator()
//        }
//    }
//
//    if (blogs.error.isNotBlank()) {
//        Box(Modifier.fillMaxSize()) {
//            Text(text = blogs.error, modifier = Modifier.align(Alignment.Center))
//        }
//    }

    val list = viewModel.pager.collectAsLazyPagingItems()

    LazyColumn {
        items(list) {
            BlogPost(it!!) {
                navController.navigate("details/${it}")
            }
        }
    }

//    if (!blogs.data.isNullOrEmpty()) {
//        LazyColumn {
//            items(blogs.data!!) {
//                BlogPost(it)
//            }
//        }
//    }

}

@Composable
fun BlogPost(blog: Blog, l: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(13.dp)
            .clickable {
                l.invoke(blog.id.toString())
            },
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(Modifier.padding(10.dp)) {
            Text(text = blog.title, fontSize = 17.sp, style = TextStyle(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(9.dp))
            Text(text = blog.body)
        }
    }
}
