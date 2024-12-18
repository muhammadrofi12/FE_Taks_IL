package com.example.fe_taks_il.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fe_taks_il.R
import com.example.fe_taks_il.data.DummyData
import com.example.fe_taks_il.models.Movies
import com.example.fe_taks_il.ui.theme.GreenActive
import com.example.fe_taks_il.ui.theme.GreenLight
import com.example.fe_taks_il.ui.theme.GreenPrimary
import com.example.fe_taks_il.ui.theme.PoppinsBold20
import com.example.fe_taks_il.ui.theme.PoppinsMedium12
import com.example.fe_taks_il.ui.theme.PoppinsRegular14

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var filteredMovies by remember { mutableStateOf(DummyData.movies) }

    // Function Search
    LaunchedEffect(searchQuery) {
        filteredMovies = if (searchQuery.isEmpty()) {
            DummyData.movies
        } else {
            DummyData.movies.filter { movie ->
                movie.judul.contains(searchQuery, ignoreCase = true) ||
                        movie.genre.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    val popularMovies = remember {
        DummyData.movies.filter { it.category == "Popular" }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Logo, Top App Bar
        Image(
            painter = painterResource(id = R.drawable.logo_rofz),
            contentDescription = "Rofz Movie Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 20.dp)
                .height(40.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Halaman Home",
            style = PoppinsMedium12,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Find Movie") },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(4.dp),
            colors = outlinedTextFieldColors(
                containerColor = Color.White,
                unfocusedBorderColor = GreenPrimary,
                focusedBorderColor = GreenActive,
                cursorColor = GreenPrimary
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 12.dp)
        ) {
            // Movie List
            items(filteredMovies.take(10)) { movie ->
                MovieCard(
                    movie = movie,
                    navController = navController
                )
            }

            // Movie Popular
            if (popularMovies.isNotEmpty()) {
                item {
                    Text(
                        text = "Popular Now",
                        modifier = Modifier.padding(20.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                item {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(popularMovies) { movie ->
                            PopularMovieCard(
                                movie = movie,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieCard(movie: Movies, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(start = 20.dp, end = 20.dp, bottom = 12.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = GreenLight
        ),
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = movie.photo),
                contentDescription = movie.judul,
                modifier = Modifier
                    .width(140.dp)
                    .height(200.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = movie.judul,
                    style = PoppinsBold20,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Genre: ${movie.genre}",
                    style = PoppinsMedium12,
                    color = GreenPrimary,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movie.sub_judul,
                    style = PoppinsRegular14,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { navController.navigate("movie_detail/${movie.id}") },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("See Detail")
                }
            }
        }
    }
}


@Composable
fun PopularMovieCard(movie: Movies, navController: NavController) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(240.dp)
            .clickable {
                navController.navigate("movie_detail/${movie.id}")
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Image(
            painter = painterResource(id = movie.photo),
            contentDescription = movie.judul,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val dummyNavController = rememberNavController()
    HomeScreen(navController = dummyNavController)
}