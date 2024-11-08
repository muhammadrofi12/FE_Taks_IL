package com.example.fe_taks_il.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.fe_taks_il.data.DummyData
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.fe_taks_il.ui.theme.GreenPrimary
import com.example.fe_taks_il.ui.theme.PoppinsBold30
import com.example.fe_taks_il.ui.theme.PoppinsMedium14
import com.example.fe_taks_il.ui.theme.PoppinsMedium16
import com.example.fe_taks_il.ui.theme.PoppinsRegular14
import androidx.compose.material3.Button as Button1


@Composable
fun DetailMovieScreen(
    movieId: Int,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val movie = DummyData.movies.find { it.id == movieId }
    val uriHandler = LocalUriHandler.current

    movie?.let {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            // Background Image
            AsyncImage(
                model = movie.photo,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .zIndex(0f),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 260.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp)
                    )
                    .zIndex(1f)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    item {
                        // Judul
                        Text(
                            text = movie.judul,
                            style = PoppinsBold30,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 16.dp)
                        )

                        // Genre
                        Text(
                            text = movie.genre,
                            style = PoppinsMedium14,
                            color = GreenPrimary,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        // Sutradara
                        Text(
                            text = "Director: ${movie.sutradara}",
                            style = PoppinsRegular14,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Deskripsi
                        Text(
                            text = "Description",
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black
                        )

                        Text(
                            text = movie.deskripsi,
                            style = PoppinsRegular14,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 8.dp),
                            textAlign = TextAlign.Justify
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Watch Trailer Button
                        Button1(
                            onClick = { uriHandler.openUri(movie.link_trailer) },
                            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "Watch Trailer",
                                style = PoppinsMedium16,
                                color = Color.White
                            )
                        }
                    }
                }
            }

            // Back Button
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.7f), CircleShape)
                    .zIndex(2f)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailMovieScreenPreview() {
    DetailMovieScreen(
            movieId = 1,
        navController = rememberNavController()
    )
}
