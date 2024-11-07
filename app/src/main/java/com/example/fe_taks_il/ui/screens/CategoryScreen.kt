package com.example.fe_taks_il.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.fe_taks_il.R
import com.example.fe_taks_il.data.DummyData
import com.example.fe_taks_il.models.Movies
import com.example.fe_taks_il.ui.theme.GreenActive
import com.example.fe_taks_il.ui.theme.GreenPrimary
import com.example.fe_taks_il.ui.theme.PoppinsMedium12

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    movies: List<Movies> = DummyData.movies,
    navController: NavController
) {
    var selectedCategory by remember { mutableStateOf("Semua") }
    val categories = listOf("Semua", "Popular", "Top Rated", "Now")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
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
            text = "Halaman Category",
            style = PoppinsMedium12,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        // Category Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                val isSelected = category == selectedCategory
                Button(
                    onClick = { selectedCategory = category },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) GreenActive else Color.White
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = if (isSelected) Color.Transparent else GreenPrimary
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(26.dp),
                    contentPadding = PaddingValues(vertical = 2.dp)
                ) {
                    Text(
                        text = category,
                        color = if (isSelected) Color.White else Color.Black,
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = 14.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Movie Grid
        val filteredMovies = when (selectedCategory) {
            "Semua" -> movies
            "Popular" -> movies.filter { it.category == "Popular" }
            "Top Rated" -> movies.filter { it.category == "Top Rated" }
            else -> movies
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxHeight()
        ) {
            items(
                items = filteredMovies,
                key = { it.id }
            ) { movie ->
                MovieitemCard(
                    movie = movie,
                    navController = navController)
            }
        }
    }
}

@Composable
fun MovieitemCard(movie: Movies, navController: NavController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { navController.navigate("movie_detail/${movie.id}") }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = movie.photo,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x99666666))
                    .align(Alignment.BottomCenter)
            ) {
                Text(
                    text = movie.judul,
                    style = TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    val dummyNavController = rememberNavController()
    CategoryScreen(navController = dummyNavController)
}
